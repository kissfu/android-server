package com.testerkit.uia.core;

public abstract class UiAutomationCore {

    public abstract Object getUiAutomation();

    public abstract void setServiceInfo(Object obj);

    public abstract NotificationListenerCore getAccessibilityEventListener();

    public abstract void disconnect();
    public abstract void connect();
}
