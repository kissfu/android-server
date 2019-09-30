package com.testerkit.uia2.core;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.UiAutomation;


import com.testerkit.common.log.Logger;
import com.testerkit.common.utils.ReflectionUtil;
import com.testerkit.uia.core.NotificationListenerCore;
import com.testerkit.uia.core.UiAutomationCore;

public class UiAutomationCore2 extends UiAutomationCore {

    private static String FUNC = "UiAutomationCore2";
    private UiAutomation uiautomation ;

    public UiAutomationCore2(Object uiautomation) {
        this.uiautomation = (UiAutomation)uiautomation;
    }


    @Override
    public Object getUiAutomation() {
        return uiautomation;
    }

    @Override
    public void setServiceInfo(Object obj) {
        uiautomation.setServiceInfo((AccessibilityServiceInfo)obj);
    }

    @Override
    public NotificationListenerCore getAccessibilityEventListener() {
        return new NotificationListenerCore2(NotificationListener.getInstance());
    }

    @Override
    public void disconnect() {
        try {
            ReflectionUtil.invoke(uiautomation,"disconnect");
        }catch (Exception e){
            Logger.iFunc(FUNC,e);
        }

    }

    @Override
    public void connect() {
        try {
            ReflectionUtil.invoke(uiautomation,"connect");
        }catch (Exception e){
            Logger.iFunc(FUNC,e);
        }
    }
}
