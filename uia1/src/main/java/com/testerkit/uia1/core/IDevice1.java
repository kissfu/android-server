package com.testerkit.uia1.core;

import android.os.RemoteException;
import android.view.accessibility.AccessibilityNodeInfo;

import com.testerkit.uia.exceptions.UIAException;
import com.testerkit.uia.interfaces.IDevice;
import com.testerkit.uia.model.KeyEnum;
import com.testerkit.uia.model.ScreenSize;

import java.util.List;

/**
 * Created by able on 2018/9/6.
 */

public class IDevice1  implements IDevice {

    com.android.uiautomator.core.UiDevice uiDevice;

    public IDevice1(com.android.uiautomator.core.UiDevice uiDevice) {
        this.uiDevice = uiDevice;
    }

    @Override
    public Object getUiDevice() {
        return uiDevice;
    }

    @Override
    public int getRotation() {
        // 该方法只适用于4.2版本以上的手机使用
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            //return uiDevice.get();
        } else {
            return -1;
        }
        return 0;
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
    public List<AccessibilityNodeInfo> getRoots() {
        return null;
    }

    @Override
    public boolean click(int x, int y, long holdTime) {
        return false;
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

}
