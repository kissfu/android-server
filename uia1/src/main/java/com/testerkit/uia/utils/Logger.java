package com.testerkit.uia.utils;


import com.testerkit.common.utils.StringUtil;

/**
 * Created by able on 2018/2/11.
 */

public class Logger {

    /**
     * 1 表示打开，0表示关闭，2并表示双开
     */
    public static int DEBUG_LOCAL = 0;

    //[总标签][uia版本][功能]
    private static  String TAG = Constants.TAG + "["+Constants.PRO + "." + Constants.VERSION + "]";

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
        switch (DEBUG_LOCAL){
            case 0:
                android.util.Log.e(TAG, getString(messages));
                break;
            case 1:
                System.err.println(TAG+getString(messages));
                break;
            case 2:
                android.util.Log.e(TAG, getString(messages));
                System.err.println(TAG+getString(messages));
                break;
        }
    }

    /**
     * Logger error
     */
    public static void error(String message, Throwable throwable) {
        switch (DEBUG_LOCAL){
            case 0:
                android.util.Log.e(TAG, message, throwable);
                break;
            case 1:
                System.err.println(TAG+message);
                break;
            case 2:
                android.util.Log.e(TAG, message, throwable);
                System.err.println(TAG+message);
                break;
        }

    }

    /**
     * Logger info
     */
    public static void info(Object... messages) {
        switch (DEBUG_LOCAL){
            case 0:
                android.util.Log.i(TAG, getString(messages));
                break;
            case 1:
                System.out.println(TAG+getString(messages));
                break;
            case 2:
                android.util.Log.i(TAG, getString(messages));
                System.out.println(TAG+getString(messages));
                break;
        }

    }
    /**
     * Logger info
     */
    public static void iFunc(String func,Object... messages) {

        if (StringUtil.isNullOrEmpty(func)) {
            info(messages);
        } else {
            info(String.format("[%s]", func),getString(messages));
        }
    }

    /**
     * Logger debug
     */
    public static void debug(Object... messages) {
        switch (DEBUG_LOCAL){
            case 0:
                android.util.Log.d(TAG, getString(messages));
                break;
            case 1:
                System.out.println(TAG+getString(messages));
                break;
            case 2:
                android.util.Log.d(TAG, getString(messages));
                System.out.println(TAG+getString(messages));
                break;
        }

    }
}
