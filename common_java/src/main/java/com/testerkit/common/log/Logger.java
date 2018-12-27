package com.testerkit.common.log;

import java.util.ArrayList;
import java.util.List;

public class Logger {


    private final static List<ILog> logList = new ArrayList<ILog>();

    public static void addLog(ILog log) {
        logList.add(log);
    }
    public static void clear(){
        logList.clear();
    }

    /**
     * Logger error
     */
    public static void error(Object... messages) {
        for (ILog log : logList) {
            log.error(messages);
        }
    }

    /**
     * Logger error
     */
    public static void error(String message, Throwable throwable) {
        for (ILog log : logList) {
            log.error(message, throwable);
        }

    }

    /**
     * Logger info
     */
    public static void info(Object... messages) {
        for (ILog log : logList) {
            log.info(messages);
        }

    }

    /**
     * Logger info
     */
    public static void iFunc(String func, Object... messages) {
        for (ILog log : logList) {
            log.iFunc(func, messages);
        }
    }

    /**
     * Logger debug
     */
    public static void debug(Object... messages) {
        for (ILog log : logList) {
            log.debug(messages);
        }
    }
}
