package com.seatSystem;

import com.seatSystem.bean.Task;
import com.seatSystem.bean.UserSeat;
import com.seatSystem.util.Log;
import top.itreatment.net.bean.*;
import top.itreatment.net.core.impl.ChooseSeatClientImpl;
import top.itreatment.net.res.Resource;
import top.itreatment.net.util.SeatUtil;
import top.itreatment.net.util.Util;

import java.util.ArrayList;
import java.util.List;

public class RunTask implements Runnable {

    private Task task;
    private List<SeatBean> recommendSeat = new ArrayList<>();
    private List<SeatBean> poIs = null;

    public RunTask(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        ChooseSeatClientImpl chooseSeatClient = new ChooseSeatClientImpl();
        List<UserSeat> userSeats = task.getUserSeats();
        for (int i = 0; i < userSeats.size(); i++) {
            UserSeat userSeat = userSeats.get(i);
            CommonBean<String> tempCommon = null;
            CommonBean<UserBean> login = chooseSeatClient.login(userSeat.getUsername(), userSeat.getPassword());
            if (Resource.SUCCESS.equals(login.getStatus())) {
                CommonBean<CategoryInfoBean> categoryInfo = chooseSeatClient.getCategoryInfo(task.getArea());
                if (Resource.SUCCESS.equals(categoryInfo.getStatus())) {

                    //处理开始时间
                    int beginTime = categoryInfo.getData().getBeginTime();
                    if (task.getBeginTime() != null) {
                        if (beginTime < task.getBeginTime() && task.getBeginTime() <= 22) {
                            beginTime = task.getBeginTime();
                        }
                    }
                    long date = categoryInfo.getData().getDate();
                    long time = Util.getTime(date * 1000, beginTime, 0, 0);
                    int dealBeginTime = (int) (time / 1000);

                    //处理持续时间
                    int dealDuration = categoryInfo.getData().getDuration();
                    if (task.getDuration() != null && task.getDuration() > categoryInfo.getData().getMax_duration()) {
                        dealDuration = categoryInfo.getData().getMax_duration();
                    }
                    dealDuration = dealDuration * 60 * 60;

                    CommonBean<CategorySeatsInfoBean> categorySeats = chooseSeatClient.searchSeats(dealBeginTime, dealDuration, userSeats.size(), task.getArea());
                    if (Resource.SUCCESS.equals(categorySeats.getStatus())) {
                        poIs = categorySeats.getData().getPOIs();
                        List<SeatBean> seatsByTitle = SeatUtil.getSeatsByTitle(poIs, Util.toArray(userSeat.getSeats(), new String[userSeat.getSeats().size()]));
                        List<SeatBean> seatBeans = SeatUtil.filerSeats(seatsByTitle, SeatUtil.NOUSE);


                        List<SeatBean> seats = categorySeats.getData().getSeats();
                        recommendSeat.addAll(seats);
                        if (!seatBeans.isEmpty()) {
                            int size = seatBeans.size();
                            String[] seatsTemp = new String[size];
                            for (int j = 0; j < size; j++) {
                                seatsTemp[j] = seatBeans.get(j).getId();
                            }
                            tempCommon = chooseSeatClient.lockSeats(dealBeginTime, dealDuration, seatsTemp);
                        } else {
                            if (!recommendSeat.isEmpty()) {
                                tempCommon = chooseSeatClient.lockSeats(dealBeginTime, dealDuration, recommendSeat.get(i).getId());
                            } else {
                                Log.eOut("没有位置可以选择");
                            }
                        }
                    }
                }
            }

            if (tempCommon != null && Resource.SUCCESS.equals(tempCommon.getStatus())) {
                List<SeatBean> seatsById = SeatUtil.getSeatsById(poIs, tempCommon.getData());
                Log.out("任务：\t" + task.getId() + "." + task.getName() + "\t用户:" + task.getUserSeats().get(i).getUsername() + "选座成功,座位号:" + seatsById.get(0).getTitle());
                task.setState(Task.State.RUNNED);
            } else {
                task.setState(Task.State.ERROR);
                Log.eOut("任务：\t" + task.getId() + "." + task.getName() + "\t用户:" + task.getUserSeats().get(i) + "选座失败");
                if (tempCommon != null && tempCommon.getMsg() != null)
                    Log.eOut("原因:" + tempCommon.getMsg());
            }

            //注销和释放座位
            chooseSeatClient.unlockallseats();
            chooseSeatClient.logout();
        }
    }
}
