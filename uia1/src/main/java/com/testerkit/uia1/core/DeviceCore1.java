package com.testerkit.uia1.core;

import android.os.RemoteException;
import android.os.SystemClock;

import com.android.uiautomator.core.UiSelector;
import com.testerkit.uia.core.UiAutomatorBridge;
import com.testerkit.uia.exceptions.UIAException;
import com.testerkit.uia.core.DeviceCore;
import com.testerkit.uia.model.KeyEnum;
import com.testerkit.uia.model.ScreenSize;
import com.testerkit.uia.utils.Logger;

/**
 * Created by able on 2018/9/6.
 */

public class DeviceCore1 extends DeviceCore {

    com.android.uiautomator.core.UiDevice uiDevice;

    public DeviceCore1(com.android.uiautomator.core.UiDevice uiDevice) {
        super(uiDevice);
        this.uiDevice = uiDevice;

        //先有uiDevice 再有uiAutomatorBridge
        uiAutomatorBridge = new UiAutomatorBridge1(uiDevice);
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
    public boolean pressKey(String keyName) throws UIAException {
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
    public boolean pressKey(int keycode) throws UIAException {
        return uiDevice.pressKeyCode(keycode);
    }

    @Override
    public boolean pressKey(int keycode,int metaState) throws UIAException {
        return uiDevice.pressKeyCode(keycode,metaState);
    }

    @Override
    public void waitForIdle() {
        try {
            uiDevice.waitForIdle();
        } catch (Exception e) {
            Logger.error("Unable wait for AUT to idle");
        }
    }

    @Override
    public void waitForIdle(long timeInMS) {
        try {
            uiDevice.waitForIdle(timeInMS);
        } catch (Exception e) {
            Logger.error("Unable wait for AUT to idle");
        }
    }

    //TODO
    public void type(String txt){

    }

}
