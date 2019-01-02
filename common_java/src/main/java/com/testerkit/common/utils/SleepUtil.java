package com.testerkit.common.utils;

public class SleepUtil {
    public static void sleep(long millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (Exception ing) {
        }
    }

    public static void sleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (Exception ing) {
        }
    }
}
