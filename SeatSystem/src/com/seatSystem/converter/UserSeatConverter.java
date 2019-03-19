package com.seatSystem.converter;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;
import com.seatSystem.bean.UserSeat;
import com.seatSystem.util.Util;

import java.util.Arrays;
import java.util.List;

public class UserSeatConverter implements IStringConverter<UserSeat> {

    @Override
    public UserSeat convert(String s) {
        String[] split = s.split(",");
        if (split.length < 2) {
            throw new ParameterException("param:" + s + "无法转换成UserSeat类型");
        }
        String username = split[0];
        String password = split[1];
        String[] seatArray = new String[split.length - 2];
        Util.subArray(split, 2, seatArray);
        List<String> seats = Arrays.asList(seatArray);
        return new UserSeat(username, password, seats);
    }
}
