package com.test;

import top.itreatment.net.bean.*;
import top.itreatment.net.core.ChooseSeatClient;
import top.itreatment.net.core.impl.ChooseSeatClientImpl;
import top.itreatment.net.res.Resource;
import top.itreatment.net.util.Util;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        //初始化客户端
        ChooseSeatClient chooseSeatClient = new ChooseSeatClientImpl();
        //登陆
        chooseSeatClient.login("201626702039", "zjb123456");

        //获取自习室列表,默认有4个
        CommonBean<List<CategoryBean>> areaList = chooseSeatClient.getAreaList();

        //取第一个自习室的id
        String id = areaList.getData().get(0).getId();

        //获取这个自习室在此刻，允许的开始时间，持续的时长和人数
        CommonBean<CategoryInfoBean> categoryInfo = chooseSeatClient.getCategoryInfo(id);

        //获取允许的最早时间戳
        long date = categoryInfo.getData().getDate();

        //将时间戳转换成整点时间
        long time = Util.getTime(date * 1000, categoryInfo.getData().getBeginTime(), 0, 0);

        //获取自习室符合要求的座位，和所有座位情况信息等
        CommonBean<CategorySeatsInfoBean> categorySeatsInfoBeanCommonBean = chooseSeatClient.searchSeats((int) (time / 1000), 3 * 60 * 60, 1, id);

        //获取符合要求的第一个座位的id
        String id1 = categorySeatsInfoBeanCommonBean.getData().getSeats().get(0).getId();

        //选择该座位，设置时长为3小时
        CommonBean<String> integerCommonBean = chooseSeatClient.lockSeats((int) (time / 1000), 3 * 60 * 60, id1);

        System.out.println(Util.toJson(integerCommonBean));

        if (Resource.SUCCESS.equals(integerCommonBean.getStatus())) {
            try {
                Thread.sleep(1000 * 20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //获取预定的座位信息列表
            CommonBean<List<MyBookingBean>> listCommonBean = chooseSeatClient.myBookingList();
            MyBookingBean myBookingBean = listCommonBean.getData().get(0);

            System.out.println(Util.toJson(myBookingBean));

            //取消所选预定的座位
            CommonBean<SeatBean> cancelbooking = chooseSeatClient.cancelbooking(myBookingBean.getId());

            System.out.println(Util.toJson(cancelbooking));
        }
        System.out.println(Util.toJson(categoryInfo));

        //注销登陆
        CommonBean<Object> logout = chooseSeatClient.logout();

        System.out.println(Util.toJson(logout));
    }
}
