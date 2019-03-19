package top.itreatment.net.core;

import top.itreatment.net.bean.*;

import java.util.List;

public interface ChooseSeatClient {

    /**
     * 登录，执行该方法后，该类实例自动保存登录的状态，该方法要先于本类的其他方法调用
     *
     * @param username 账号名
     * @param password 密码
     * @return 获取的用户实例UserBean，包含基本信息
     */
    default CommonBean<UserBean> login(String username, String password) {
        return null;
    }

    /**
     * 注销，登录操作后，在不需要的时候可以进行注销操作，不必须，服务器会自动清除登录状态
     *
     * @return 通过CommonBean实例判断是否注销成功
     */
    default CommonBean<Object> logout() {
        return null;
    }

    /**
     * 获取自习室列表
     *
     * @return CommonBean<List<CategoryBean>>的实例
     */
    default CommonBean<List<CategoryBean>> getAreaList() {
        return null;
    }

    /**
     * 获取登录实例的预约座位列表
     *
     * @return CommonBean<List<MyBookingBean>>实例
     */
    default CommonBean<List<MyBookingBean>> myBookingList() {
        return null;
    }

    /**
     * 获取这个时间，对应id的自习室的限制，时长限制，开始时间，人数等
     *
     * @param contentId 获取的自习室实例的ID
     * @return CommonBean<CategoryInfoBean>实例
     */
    default CommonBean<CategoryInfoBean> getCategoryInfo(String contentId) {
        return null;
    }


    /**
     * 搜索座位，获取所有座位信息
     *
     * @param beginTime 开始时间，时间戳/1000精确到秒
     * @param duration  时长，以小时为单位
     * @param num       获取推荐座位的数量
     * @param contentId 自习室对应id
     * @return CommonBean<CategorySeatsInfoBean>，包含所有座位信息，推荐座位，和如果是多个推荐座位，他们的间距
     */
    default CommonBean<CategorySeatsInfoBean> searchSeats(Integer beginTime, Integer duration, Integer num, String contentId) {
        return null;
    }

    /**
     * 选定某个座位
     *
     * @param beginTime 开始时间，时间戳/1000精确到秒
     * @param duration  时长，以小时为单位
     * @param seats     传入多个座位id，有序的选座，失败就用下一个id选座，所有id都选座失败，或是有一个成功方法结束
     * @return CommonBean<String> 包含是否成功
     */
    default CommonBean<String> lockSeats(Integer beginTime, Integer duration, String... seats) {
        return null;
    }

    /**
     * 解除推荐座位锁定，当执行searchSeats方法时，服务器会将推荐座位进行锁定，其他人无法选择的状态，该方法即可解除
     *
     * @return CommonBean 是否解除成功
     */
    default CommonBean unlockallseats() {
        return null;
    }

    /**
     * 取消预选座位
     *
     * @param bookingId 传入预选的预定id
     * @return 是否取消成功
     */
    default CommonBean cancelbooking(Integer bookingId) {
        return null;
    }

    /**
     * 获取指定自习室的座位情况
     *
     * @param contentld 自习室id
     * @return CommonBean<List<SeatBean>>实例
     */
    default CommonBean<List<SeatBean>> getBlankPOIs(String contentld) {
        return null;
    }

    /**
     * 获取指定自习室的推荐座位
     *
     * @param contentld 自习室id
     * @return CommonBean<List<SeatBean>>实例
     */
    default CommonBean<List<SeatBean>> getRecommendSeat(String contentld) {
        return null;
    }
}
