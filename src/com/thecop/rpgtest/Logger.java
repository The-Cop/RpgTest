package com.thecop.rpgtest;

/**
 * Created by Admin on 20.03.2015.
 */
public class Logger {
    private static boolean debug = true;

    public static void dlog(Object o) {
        if (debug) {
            log(o);
        }
    }

    public static void log(Object o) {
        System.out.println(o.toString());
    }
}
