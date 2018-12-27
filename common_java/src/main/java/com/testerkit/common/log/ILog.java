package com.testerkit.common.log;

public interface ILog {

    void error(Object... messages);

    void error(String message, Throwable throwable);

    void info(Object... messages);

    void iFunc(String func, Object... messages);

    void debug(Object... messages);
}
