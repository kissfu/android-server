package com.testerkit.uia2.core;

import android.os.RemoteException;

import com.testerkit.uia.core.UiAutomatorBridge;
import com.testerkit.uia.core.DeviceCore;
import com.testerkit.uia.model.KeyEnum;
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
        uiDevice.wakeUp();
    }

    @Override
    public ScreenSize getScreenSize() {
        return new ScreenSize(uiDevice.getDisplayWidth(),uiDevice.getDisplayHeight());
    }

    @Override
    public boolean pressKey(String keyName) {
        KeyEnum keyEnum = KeyEnum.valueOf(keyName);
        switch (keyEnum) {
            case MENU:
                return uiDevice.pressMenu();
            case BACK:
                return uiDevice.pressBack();
            case HOME:
                return uiDevice.pressHome();
            case SEARCH:
                return uiDevice.pressSearch();
            case ENTER:
                return uiDevice.pressEnter();
            case LEFT:
                return uiDevice.pressDPadLeft();
            case RIGHT:
                return uiDevice.pressDPadRight();
            case UP:
                return uiDevice.pressDPadUp();
            case DOWN:
                return uiDevice.pressDPadDown();
            case CENTER:
                return uiDevice.pressDPadCenter();
        }
        return false;
    }

    @Override
    public boolean pressKey(int keycode) {
        return uiDevice.pressKeyCode(keycode);
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
