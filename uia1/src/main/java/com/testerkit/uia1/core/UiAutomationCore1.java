package com.testerkit.uia1.core;

import com.testerkit.uia.core.NotificationListenerCore;
import com.testerkit.uia.core.UiAutomationCore;

public class UiAutomationCore1 extends UiAutomationCore {

    public UiAutomationCore1(Object uiautomation) {

    }

    @Override
    public Object getUiAutomation() {
        return null;
    }

    @Override
    public void setServiceInfo(Object obj) {

    }

    @Override
    public NotificationListenerCore getAccessibilityEventListener() {
        return new NotificationListenerCore1(null);
    }

    @Override
    public void disconnect() {

    }

    @Override
    public void connect() {

    }
}
