package com.seatSystem.util;

import com.beust.jcommander.ParameterException;
import com.seatSystem.bean.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Util {

    public static <T> T[] subArray(T[] ts, int start, T[] target) {
        return subArray(ts, start, ts.length, target);
    }

    public static <T> T[] subArray(T[] ts, int start, int end, T[] target) {
        if (ts == null) {
            throw new NullPointerException("ts 不可以为空");
        } else if (start <= -1 || end <= -1) {
            throw new RuntimeException("位置下标不能为负数");
        } else if (ts.length < start || end > ts.length) {
            throw new ParameterException("ts:" + Arrays.asList(ts) + "\nstart:" + start + "\nend:" + end);
        }

        int j = 0;
        for (int i = start; i < end; i++) {
            target[j++] = ts[i];
        }
        return target;
    }

    public static String formatDate(Date date) {
        return formatDate(date, "yyyy-MM-dd/HH:mm:ss");
    }

    public static String formatDate(Date date, String formatStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
        String format = simpleDateFormat.format(date);
        return format;
    }

    public static <T, F> List<F> getItemParameter(List<T> ts, Function<T, F> function) {
        List<F> os = new ArrayList<>();
        ts.forEach(t -> {
            F apply = function.apply(t);
            os.add(apply);
        });
        return os;
    }


    public static String formatStrWidth(Object o, int width, int tag) {
        String s = String.valueOf(o);
        int length = s.length();
        String format;
        switch (tag) {
            case 1:
                format = String.format("%" + width + "s", s);
                break;
            case -1:
                format = String.format("%-" + width + "s", s);
                break;
            case 0:
                int borderWidth = (width - length) / 2;
                if (borderWidth > 0) {
                    format = String.format("%-" + (width - borderWidth) + "s", s);
                    format = String.format("%" + width + "s", format);
                } else {
                    format = s;
                }
                break;
            default:
                format = String.format("%" + width + "s", s);
                break;
        }
        return format;
    }
}
