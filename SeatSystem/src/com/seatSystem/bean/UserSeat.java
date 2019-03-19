package com.seatSystem.bean;

import java.util.Arrays;
import java.util.List;

public class UserSeat {

    private String username;
    private String password;
    private List<String> seats;

    public UserSeat() {
    }

    public UserSeat(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserSeat(String username, String password, List<String> seats) {
        this.username = username;
        this.password = password;
        this.seats = seats;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "{" + username + "," + password + "," + seats + "}";
    }
}
