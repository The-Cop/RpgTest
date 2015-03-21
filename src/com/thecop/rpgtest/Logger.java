package com.thecop.rpgtest;

/**
 * Created by Admin on 20.03.2015.
 */
public class Logger {
    private static boolean debug = true;

    public static void dlog(Object o) {
        if (debug) {
            print(o);
        }
    }

    public static void print(Object o) {
        System.out.println(o.toString());
    }
}
