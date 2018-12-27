package com.testerkit.common.log;

import com.testerkit.common.utils.StringUtil;

public class LogPrint extends LogBase {


    @Override
    public void error(Object... messages) {
        System.err.println(TAG + getString(messages));
    }

    @Override
    public void error(String message, Throwable throwable) {
        System.err.println(message);
        if (throwable != null) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void info(Object... messages) {
        System.out.println(TAG + getString(messages));
    }

    @Override
    public void debug(Object... messages) {
        System.out.println(TAG + getString(messages));
    }
}
