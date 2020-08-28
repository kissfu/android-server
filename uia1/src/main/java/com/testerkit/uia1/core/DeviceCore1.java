package com.testerkit.uia1.core;

import android.graphics.Point;
import android.os.RemoteException;

import com.testerkit.common.enums.AppCategory;
import com.testerkit.common.enums.KeyEnum;
import com.testerkit.common.enums.UIAType;
import com.testerkit.common.exceptions.UIAException;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.AppInfo;
import com.testerkit.common.shell.ShellExecutor;
import com.testerkit.common.utils.ReflectionUtil;
import com.testerkit.common.utils.StringUtil;
import com.testerkit.uia.core.DeviceCore;
import com.testerkit.uia.core.UiAutomatorBridge;
import com.testerkit.uia.model.AndroidElement;
import com.testerkit.uia.model.ScreenSize;
import com.testerkit.uia1.model.UiObjectElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by able on 2018/9/6.
 */

public class DeviceCore1 extends DeviceCore {

    com.android.uiautomator.core.UiDevice uiDevice;

    public DeviceCore1(com.android.uiautomator.core.UiDevice uiDevice, UIAType type) {
        super(uiDevice,type);
        this.uiDevice = uiDevice;

        //先有uiDevice 再有uiAutomatorBridge
        uiAutomatorBridge = new UiAutomatorBridge1(uiDevice,type);
        //仅仅是为了方便访问,可以通过device 访问
        UiAutomatorBridge.setINSTANCE(uiAutomatorBridge);
    }

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

    //region wakeup

    @Override
    public void wake() throws RemoteException {
        uiDevice.wakeUp();
    }

    //endregion

    //region deviceinfo

    @Override
    public ScreenSize getDisplaySize() {
        return new ScreenSize(uiDevice.getDisplayWidth(), uiDevice.getDisplayHeight());
    }

    @Override
    public ScreenSize getDisplaySizeDp() {
        Point point = uiDevice.getDisplaySizeDp();
        if(point == null){
            return null;
        }
        return new ScreenSize(point.x,point.y);
    }

    //endregion

    //region pressKey

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
    public boolean pressKey(int keycode, int metaState) throws UIAException {
        return uiDevice.pressKeyCode(keycode, metaState);
    }

    //endregion

    //region waitForIdle

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

    //endregion

    @Override
    public boolean type(String text) {
        AndroidElement objectElement = new UiObjectElement(null, null);
        objectElement.typeDefault(text);
        return true;
    }

    @Override
    public boolean typeFromClipBoard() {
        return false;
    }

    @Override
    public boolean swipe(int startX, int startY, int endX, int endY, int steps){
        try {
            boolean success = uiDevice.swipe(startX,startY,endX,endY,steps);
            //滑屏幕之后手动清理缓存
            ReflectionUtil.clearAccessibilityCache();
            return success;
        } catch (Exception e) {
            Logger.error( e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean swipe(Point[] segments, int segmentSteps){
        try {
            boolean success = uiDevice.swipe(segments, segmentSteps);
            //滑屏幕之后手动清理缓存
            ReflectionUtil.clearAccessibilityCache();
            return success;
        } catch (Exception e) {
            Logger.error( e.getMessage(), e);
        }
        return false;
    }

    @Override
    public List<AppInfo> getAppList(AppCategory category) {
        List<AppInfo> apps = new ArrayList<AppInfo>();
        String cmd = "";
        int timeout = 5 * 1000;
        switch (category) {
            case ALL:
                cmd = "pm list packages";
                break;
            case USER:
                cmd = "pm list packages -3";
                break;
            case SYSTEM:
                cmd = "pm list packages -s";
                break;
        }
        if (StringUtil.isEmpty(cmd)) {
            return apps;
        }
        try {
            String res = ShellExecutor.executeCommand(null, cmd, timeout);
            String[] lines = res.split("\r\n");
            for (String str : lines) {
                if (StringUtil.isEmpty(str)) {
                    continue;
                }
                AppInfo info = new AppInfo();
                info.setPackageName(str.substring("package:".length()));
                apps.add(info);
            }
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }

        return apps;
    }
}
