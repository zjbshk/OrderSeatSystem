package top.itreatment.net.bean;

import java.util.List;

public class CategorySeatsInfoBean {


    /**
     * rank : 0
     * id : 1177
     * title : 二楼北自习室
     * storey : 二楼
     */
    private String id;
    private String rank;

    private String title;
    private String storey;

    private List<SeatBean> seats;
    private Integer distance;
    private List<SeatBean> POIs;

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStorey(String storey) {
        this.storey = storey;
    }

    public String getRank() {
        return rank;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStorey() {
        return storey;
    }

    public List<SeatBean> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatBean> seats) {
        this.seats = seats;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public List<SeatBean> getPOIs() {
        return POIs;
    }

    public void setPOIs(List<SeatBean> POIs) {
        this.POIs = POIs;
    }
}
