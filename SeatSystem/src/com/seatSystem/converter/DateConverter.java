package com.seatSystem.converter;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.seatSystem.bean.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements IStringConverter<Date> {

    @Override
    public Date convert(String s) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
        try {
            Date parse = dateFormat.parse(s);
            return parse;
        } catch (ParseException e) {
            throw new ParameterException("时间解析异常," + e.getMessage());
        }
    }
}
