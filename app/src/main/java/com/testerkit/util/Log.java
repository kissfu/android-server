package com.testerkit.util;


public class Log {

    private final static String TAG = "[TARGET2]--->";

    public static void error(Object... messages) {
        android.util.Log.e(TAG, getString(messages));
    }

    public static void error(String message, Throwable throwable) {
        android.util.Log.e(TAG, message, throwable);
    }


    public static void info(Object... messages) {
        android.util.Log.i(TAG, getString(messages));
    }


    public static void debug(Object... messages) {
        android.util.Log.d(TAG, getString(messages));
    }

    protected static String getString(Object... args) {
        StringBuilder content = new StringBuilder();
        Object[] var3 = args;
        int var4 = args.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            Object arg = var3[var5];
            if (arg != null) {
                content.append(arg.toString());
            }
        }

        return content.toString();
    }
}
