package top.itreatment.net.util;

import top.itreatment.net.bean.SeatBean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatUtil {

    //    状态(0,未被使用),(1 ,正在使用) ,(2,以及被推荐等待确认),(3,)(4,)
    public static final Integer NOUSE = 0;
    public static final Integer USING = 1;
    public static final Integer RECOMMEND = 2;
    public static final Integer NUKNOW = 3;


    public static List<SeatBean> filerSeats(List<SeatBean> seatBeans, Integer state) {
        List<SeatBean> filerSeatBeans = new ArrayList<>();
        seatBeans.forEach(poi -> {
            if (state == poi.getState()) {
                filerSeatBeans.add(poi);
            }
        });
        return filerSeatBeans;
    }


    public static Map<Integer, List<SeatBean>> filerSeats(List<SeatBean> seatBeans) {
        Map<Integer, List<SeatBean>> filerSeatBeansMap = new HashMap<>();
        seatBeans.forEach(poi -> {
            int state = poi.getState();
            List<SeatBean> seatBeansTemp = filerSeatBeansMap.get(state);
            if (seatBeansTemp == null) {
                seatBeansTemp = new ArrayList<>();
                filerSeatBeansMap.put(state, seatBeansTemp);
            }
            seatBeansTemp.add(poi);
        });
        return filerSeatBeansMap;
    }

    public static List<SeatBean> getSeatsByTitle(List<SeatBean> seatBeans, String... titles) {
        return getSeatsByInfo(seatBeans, "title", titles);
    }

    public static List<SeatBean> getSeatsById(List<SeatBean> seatBeans, String... ids) {
        return getSeatsByInfo(seatBeans, "id", ids);
    }

    private static List<SeatBean> getSeatsByInfo(List<SeatBean> seatBeans, String tag, String... tags) {
        List<SeatBean> filerSeatBeans = new ArrayList<>();
        seatBeans.forEach(poi -> {
            try {
                Field declaredField = SeatBean.class.getDeclaredField(tag);
                declaredField.setAccessible(true);
                String o = (String) declaredField.get(poi);
                if (Util.isIn(o, tags)) {
                    filerSeatBeans.add(poi);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return filerSeatBeans;
    }

}
