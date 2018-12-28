package com.testerkit.uia.log;

import com.testerkit.common.log.LogBase;

public class LogAndroid extends LogBase {

    @Override
    public void error(Object... messages) {
        android.util.Log.e(TAG, getString(messages));
    }

    @Override
    public void error(String message, Throwable throwable) {
        android.util.Log.e(TAG, message, throwable);
    }

    @Override
    public void info(Object... messages) {
        android.util.Log.i(TAG, getString(messages));
    }

    @Override
    public void debug(Object... messages) {
        android.util.Log.d(TAG, getString(messages));
    }
}
