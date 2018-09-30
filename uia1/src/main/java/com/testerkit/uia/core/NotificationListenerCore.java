package com.testerkit.uia.core;

import java.util.List;

public abstract class NotificationListenerCore {

    public abstract void start();
    public abstract void stop();
    public abstract List<CharSequence> getToastMessage();

    public abstract boolean isListening();

}
