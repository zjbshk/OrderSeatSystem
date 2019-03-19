package top.itreatment.net.bean;

import java.util.List;

public class SeatBean {


    /**
     * category_id : 607
     * group_id : 62
     * w : 2
     * x : 64
     * h : 2
     * y : 33
     * recommend : true
     * id : 26914
     * state : 2
     * title : 227
     * locker : []
     */

    private String id;
    private int state;
    private String title;
    private List<?> locker;

    private String category_id;
    private String group_id;
    private String w;
    private String x;
    private String h;
    private String y;

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public void setW(String w) {
        this.w = w;
    }

    public void setX(String x) {
        this.x = x;
    }

    public void setH(String h) {
        this.h = h;
    }

    public void setY(String y) {
        this.y = y;
    }



    public void setId(String id) {
        this.id = id;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocker(List<?> locker) {
        this.locker = locker;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public String getW() {
        return w;
    }

    public String getX() {
        return x;
    }

    public String getH() {
        return h;
    }

    public String getY() {
        return y;
    }


    public String getId() {
        return id;
    }

    public int getState() {
        return state;
    }

    public String getTitle() {
        return title;
    }

    public List<?> getLocker() {
        return locker;
    }
}
