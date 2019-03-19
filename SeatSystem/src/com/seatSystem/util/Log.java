package com.seatSystem.util;

import java.io.PrintStream;

public class Log {


    public static void out(String tip) {
        System.out.println(tip);
    }

    public static void eOut(String tip) {
        PrintStream err = System.err;
        err.append(tip).append("\n");
    }
}
