package com.testerkit.uia1.core;

import com.testerkit.uia.core.NotificationListenerCore;

import java.util.List;

public class NotificationListenerCore1 extends NotificationListenerCore {

    //for uia1
    private Object notificationListener;

    public NotificationListenerCore1(Object notificationListener) {
        this.notificationListener = notificationListener;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public List<CharSequence> getToastMessage() {
        return null;
    }

    @Override
    public boolean isListening() {
        return false;
    }
}
