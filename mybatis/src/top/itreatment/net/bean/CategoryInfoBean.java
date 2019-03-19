package top.itreatment.net.bean;

public class CategoryInfoBean {
    /**
     * date : 1550885999
     * duration : 3
     * beginTime : 9
     * num : 1
     * max_date : 1550937600
     * minBeginTime : 7
     * maxEndTime : 22
     * min_duration : 3
     * max_duration : 15
     * max_num : 2
     */
    private long date;
    private int duration;
    private int beginTime;
    private int num;
    private int max_date;
    private int maxEndTime;
    private int min_duration;
    private int minBeginTime;
    private int max_duration;
    private int max_num;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setMax_date(int max_date) {
        this.max_date = max_date;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setMaxEndTime(int maxEndTime) {
        this.maxEndTime = maxEndTime;
    }

    public void setMin_duration(int min_duration) {
        this.min_duration = min_duration;
    }

    public void setMinBeginTime(int minBeginTime) {
        this.minBeginTime = minBeginTime;
    }

    public void setBeginTime(int beginTime) {
        this.beginTime = beginTime;
    }

    public void setMax_duration(int max_duration) {
        this.max_duration = max_duration;
    }

    public void setMax_num(int max_num) {
        this.max_num = max_num;
    }


    public int getDuration() {
        return duration;
    }

    public int getMax_date() {
        return max_date;
    }

    public int getNum() {
        return num;
    }

    public int getMaxEndTime() {
        return maxEndTime;
    }

    public int getMin_duration() {
        return min_duration;
    }

    public int getMinBeginTime() {
        return minBeginTime;
    }

    public int getBeginTime() {
        return beginTime;
    }

    public int getMax_duration() {
        return max_duration;
    }

    public int getMax_num() {
        return max_num;
    }

    @Override
    public String toString() {
        return "CategoryInfoBean{" +
                "date=" + date +
                ", duration=" + duration +
                ", beginTime=" + beginTime +
                ", num=" + num +
                ", max_date=" + max_date +
                ", maxEndTime=" + maxEndTime +
                ", min_duration=" + min_duration +
                ", minBeginTime=" + minBeginTime +
                ", max_duration=" + max_duration +
                ", max_num=" + max_num +
                '}';
    }
}
