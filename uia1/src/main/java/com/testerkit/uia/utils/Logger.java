package com.testerkit.uia.utils;


/**
 * Created by able on 2018/2/11.
 */

public class Logger {
    //[总标签][uia版本][功能]
    private static  String TAG = Constants.TAG + Constants.VERSION;

    private static String getString(Object... args) {
        StringBuilder content = new StringBuilder();

        for (Object arg : args) {
            if (arg != null) {
                content.append(arg.toString());
            }
        }

        return content.toString();
    }

    /**
     * Logger error
     */
    public static void error(Object... messages) {
        android.util.Log.e(TAG, getString(messages));
    }

    /**
     * Logger error
     */
    public static void error(String message, Throwable throwable) {
        android.util.Log.e(TAG, getString(message), throwable);
    }

    /**
     * Logger info
     */
    public static void info(Object... messages) {
        android.util.Log.i(TAG, getString(messages));
    }
    /**
     * Logger info
     */
    public static void info(String functionDescription,Object... messages) {

        android.util.Log.i(TAG + String.format( "[%s]" , functionDescription), getString(messages));
    }

    /**
     * Logger debug
     */
    public static void debug(Object... messages) {
        android.util.Log.d(TAG, getString(messages));
    }
}
