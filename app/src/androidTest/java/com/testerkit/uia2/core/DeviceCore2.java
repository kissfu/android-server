package com.testerkit.uia2.core;

import android.os.RemoteException;

import com.testerkit.uia.core.UiAutomatorBridge;
import com.testerkit.uia.core.DeviceCore;
import com.testerkit.uia.model.ScreenSize;

/**
 * Created by able on 2018/9/6.
 */

public class DeviceCore2 extends DeviceCore {

    android.support.test.uiautomator.UiDevice uiDevice;

    public DeviceCore2(android.support.test.uiautomator.UiDevice uiDevice) {
        super(uiDevice);
        this.uiDevice = uiDevice;
        //先有uiDevice 再有uiAutomatorBridge
        uiAutomatorBridge = new UiAutomatorBridge2(uiDevice);
        //仅仅是为了方便访问,可以通过device 访问
        UiAutomatorBridge.setINSTANCE(uiAutomatorBridge);
    }



    @Override
    public Object getUiDevice() {
        return uiDevice;
    }

    @Override
    public void wake() throws RemoteException {

    }

    @Override
    public ScreenSize getScreenSize() {
        return null;
    }


    @Override
    public boolean click(int x, int y, long holdTime) {
        return false;
    }

    @Override
    public boolean pressKey(String keyName) {
        return false;
    }

    @Override
    public boolean pressKey(int keycode) {
        return false;
    }

    @Override
    public void waitForIdle() {
        uiDevice.waitForIdle();
    }

    @Override
    public void waitForIdle(long timeInMS) {
        uiDevice.waitForIdle(timeInMS);
    }

}
