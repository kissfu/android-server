package com.testerkit.uia2.core;

import android.os.RemoteException;
import android.view.accessibility.AccessibilityNodeInfo;

import com.testerkit.uia.interfaces.IDevice;
import com.testerkit.uia.model.ScreenSize;

import java.util.List;

/**
 * Created by able on 2018/9/6.
 */

public class IDevice2 implements IDevice {

    android.support.test.uiautomator.UiDevice uiDevice;

    public IDevice2(android.support.test.uiautomator.UiDevice uiDevice) {
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
            return uiDevice.getDisplayRotation();
        }
        return -1;
    }

    @Override
    public void wake() throws RemoteException {

    }

    @Override
    public ScreenSize getScreenSize() {
        return null;
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
    public boolean pressKey(String keyName) {
        return false;
    }

    @Override
    public boolean pressKey(int keycode) {
        return false;
    }

}
