package com.testerkit.uia1.core;

import com.testerkit.uia.core.ReturningRunnable;

/**
 * Created by able on 2018/9/7.
 */

public  class EventRegister1 {

    private static final int EVENT_COOLDOWN_MS = 750;


    public static Boolean runAndRegisterScrollEvents(ReturningRunnable<Boolean> runnable, long timeout) {
        runnable.run();

        return runnable.getResult();
    }



    public static Boolean runAndRegisterScrollEvents(ReturningRunnable<Boolean> runnable) {
        int timeout = EVENT_COOLDOWN_MS;

        return runAndRegisterScrollEvents(runnable, timeout);
    }
}
