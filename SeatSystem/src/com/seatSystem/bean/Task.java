package com.seatSystem.bean;

import com.beust.jcommander.Parameter;
import com.seatSystem.converter.DateConverter;
import com.seatSystem.converter.StateConverter;
import com.seatSystem.converter.UserSeatConverter;
import com.seatSystem.util.Util;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class Task {

    private Integer id;

    @Parameter(names = {"-n", "--name"}, description = "所创建的任务的名称")
    private String name;

    @Parameter(names = {"-t", "--time"}, description = "任务执行时间", converter = DateConverter.class)
    private Date time;

    @Parameter(names = {"-s", "--state"}, description = "任务状态", converter = StateConverter.class)
    private State state = State.NOT_ENABLED;

    @Parameter(names = {"-b", "-beginTime"}, description = "开始时间")
    private Integer beginTime;

    @Parameter(names = {"-a", "--area"}, description = "自习室id编号")
    private String area;

    @Parameter(names = {"-d", "--duration"}, description = "时长")
    private Integer duration;

    @Parameter(names = {"-u", "--userSeats"}, description = "选座用户", listConverter = UserSeatConverter.class)
    private List<UserSeat> userSeats;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<UserSeat> getUserSeats() {
        return userSeats;
    }

    public void setUserSeats(List<UserSeat> userSeats) {
        this.userSeats = userSeats;
    }

    public Integer getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
    }

    public enum State {
        NOT_ENABLED,
        RUNNING,
        RUNNED,
        DISABLED,
        BEFORE,
        ERROR,
        PREPARE
    }


    @Override
    public String toString() {
        int tag = -1;
        return Util.formatStrWidth(id, 5, 0)
                + Util.formatStrWidth(name, 15, 0)
                + Util.formatStrWidth((time == null ? null : Util.formatDate(time)), 25, tag)
                + Util.formatStrWidth(state, 15, tag)
                + Util.formatStrWidth(area, 10, 0)
                + Util.formatStrWidth(beginTime, 6, 0)
                + Util.formatStrWidth(duration, 6, 0)
                + (userSeats == null ? null : Util.getItemParameter(userSeats, UserSeat::getUsername));
    }
}
