package com.testerkit.uia2.core;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;

import com.testerkit.common.enums.AppCategory;
import com.testerkit.common.enums.KeyEnum;
import com.testerkit.common.model.AppInfo;
import com.testerkit.common.utils.StringUtil;
import com.testerkit.uia.core.UiAutomatorBridge;
import com.testerkit.uia.core.DeviceCore;
import com.testerkit.uia.model.AndroidElement;

import com.testerkit.uia.model.ScreenSize;
import com.testerkit.uia2.model.UiObject2Element;

import java.util.ArrayList;
import java.util.List;

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
        return new ScreenSize(uiDevice.getDisplayWidth(), uiDevice.getDisplayHeight());
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
    public boolean pressKey(int keycode, int metaState) {
        return uiDevice.pressKeyCode(keycode, metaState);
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

    @Override
    public boolean type(String text) {
        AndroidElement objectElement = new UiObject2Element(null, null);

        objectElement.typeDefault(text);
        return true;
    }

    @Override
    public List<AppInfo> getAppList(AppCategory category) {
        List<AppInfo> apps = new ArrayList<AppInfo>();
        Context ctx = InstrumentationRegistry.getInstrumentation().getContext();
        PackageManager packageManager = ctx.getPackageManager();
        List<PackageInfo> packs = packageManager.getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if (p.versionName == null) {
                continue;
            }
            AppInfo newInfo = new AppInfo();
            //过滤掉系统app
            if ((ApplicationInfo.FLAG_SYSTEM & p.applicationInfo.flags) != 0) {
                newInfo.setSystem(true);
            }
            newInfo.setAppName(p.applicationInfo.loadLabel(packageManager).toString());
            newInfo.setPackageName(p.packageName);
            newInfo.setVersionName(p.versionName);
            newInfo.setVersionCode(p.versionCode);
            //newInfo.setIcon( p.applicationInfo.loadIcon(packageManager));
            newInfo.setLaunchActivity(getLaunchActivity(p.packageName, packageManager));
            if (StringUtil.isEmpty(newInfo.getLaunchActivity())) {
                continue;
            }
            apps.add(newInfo);
        }
        List<AppInfo> appsResult = new ArrayList<AppInfo>();
        switch (category){
            case ALL:
                appsResult = apps;
                break;
            case USER:
                for (AppInfo app:apps) {
                    if(app.isSystem() == true){
                        continue;
                    }
                    appsResult.add(app);
                }
                break;
            case SYSTEM:
                for (AppInfo app:apps) {
                    if(app.isSystem() == false){
                        continue;
                    }
                    appsResult.add(app);
                }
                break;
        }
        return appsResult;
    }

    private String getLaunchActivity(String pn, PackageManager packageManager) {
        String str = "";
        try {
            Intent intent = packageManager.getLaunchIntentForPackage(pn);
            if (intent != null && intent.getComponent() != null) {
                str = intent.getComponent().getClassName();
            }
        } catch (Exception e) {
        }

        return str;
    }


}
