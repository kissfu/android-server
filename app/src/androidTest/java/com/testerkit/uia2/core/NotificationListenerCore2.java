package com.testerkit.uia2.core;

import com.testerkit.uia.core.NotificationListenerCore;

import java.util.List;

public class NotificationListenerCore2 extends NotificationListenerCore {

    private NotificationListener notificationListener;

    public NotificationListenerCore2(NotificationListener notificationListener) {
        this.notificationListener = notificationListener;
    }

    @Override
    public void start() {
        notificationListener.start();
    }

    @Override
    public void stop() {
        notificationListener.stop();
    }

    @Override
    public List<CharSequence> getToastMessage() {
        return notificationListener.getToastMessage();
    }

    @Override
    public boolean isListening() {
        return notificationListener.isListening();
    }
}
