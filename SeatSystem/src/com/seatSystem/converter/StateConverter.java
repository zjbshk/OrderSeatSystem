package com.seatSystem.converter;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;
import com.seatSystem.bean.Task;
import com.seatSystem.bean.UserSeat;
import com.seatSystem.util.Util;

import java.util.Arrays;

public class StateConverter implements IStringConverter<Task.State> {

    @Override
    public Task.State convert(String s) {
        Task.State[] values = Task.State.values();
        for (Task.State value : values) {
            if (value.name().equalsIgnoreCase(s)) {
                return value;
            }
        }
        return Task.State.NOT_ENABLED;
    }
}
