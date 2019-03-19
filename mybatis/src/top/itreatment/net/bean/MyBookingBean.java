package top.itreatment.net.bean;

public class MyBookingBean {


    /**
     * ifSponsor : true
     * limitLeftBack : 3600
     * status : 4
     * roomName : 二楼北自习室
     * addr : 二楼
     * url : /Seat/Index/bookingInfo?bookingId=2015891&fromType=1
     * nowTime : 1550919563
     * duration : 6931
     * seatNum : 227
     * orderTime : 1550905469
     * limitSignBack : 1800
     * time : 1550905469
     * id : 2015891
     * limitSignAgo : 900
     */

    private boolean ifSponsor;
    private int limitLeftBack;
    private String roomName;
    private String url;
    private int nowTime;
    private String duration;
    private String seatNum;
    private String orderTime;
    private int limitSignBack;
    private String time;
    private int id;
    private String addr;
    private String status;
    private int limitSignAgo;

    public void setIfSponsor(boolean ifSponsor) {
        this.ifSponsor = ifSponsor;
    }

    public void setLimitLeftBack(int limitLeftBack) {
        this.limitLeftBack = limitLeftBack;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setNowTime(int nowTime) {
        this.nowTime = nowTime;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public void setLimitSignBack(int limitSignBack) {
        this.limitSignBack = limitSignBack;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLimitSignAgo(int limitSignAgo) {
        this.limitSignAgo = limitSignAgo;
    }

    public boolean isIfSponsor() {
        return ifSponsor;
    }

    public int getLimitLeftBack() {
        return limitLeftBack;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getUrl() {
        return url;
    }

    public int getNowTime() {
        return nowTime;
    }

    public String getDuration() {
        return duration;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public int getLimitSignBack() {
        return limitSignBack;
    }

    public String getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public String getAddr() {
        return addr;
    }

    public String getStatus() {
        return status;
    }

    public int getLimitSignAgo() {
        return limitSignAgo;
    }
}
