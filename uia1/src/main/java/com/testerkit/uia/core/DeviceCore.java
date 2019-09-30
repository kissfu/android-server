package com.testerkit.uia.core;

import android.os.SystemClock;
import android.view.Display;
import android.view.accessibility.AccessibilityNodeInfo;

import com.testerkit.common.enums.AppCategory;
import com.testerkit.common.enums.ClickPosition;
import com.testerkit.common.enums.UIAType;
import com.testerkit.common.exceptions.UIAException;
import com.testerkit.common.exceptions.UIANotConnected;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.AppInfo;
import com.testerkit.common.model.RectInfo;
import com.testerkit.common.utils.ReflectionUtil;
import com.testerkit.uia.model.ScreenSize;
import com.testerkit.uia.utils.SystemUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by able on 2018/2/13.
 * 1、公用方法是通过反射执行
 * 2、子类方法是通过具体的uiDevice执行方法
 */

public abstract class DeviceCore {
    private static String FUNC = "DeviceCore";

    public abstract Object getUiDevice();

    public abstract void wake() throws android.os.RemoteException;

    public abstract ScreenSize getScreenSize();

    //List<String> getRootsPackageName

    /**
     * 根据给定的按键名称，执行按键。
     *
     * @param keyName 按键名称。
     * @return true/false 表示按键是否执行成功。
     */
    public abstract boolean pressKey(String keyName);

    /**
     * 这里的metastate实际上就是控制按键，比如说shift键，alt键，ctrl键等控制键
     *
     * @param keycode
     * @param metaState 默认是 0
     * @return
     */
    public abstract boolean pressKey(int keycode, int metaState);

    /**
     * 给定keycode，由UIDevice执行press keycode.
     *
     * @param keycode
     * @return true/false 表示按键是否执行成功。
     */
    public abstract boolean pressKey(int keycode);

    public abstract void waitForIdle();

    public abstract void waitForIdle(long timeInMS);

    public abstract boolean type(String text);

    public abstract List<AppInfo> getAppList(AppCategory category);

    //region public method
    protected UiAutomatorBridge uiAutomatorBridge;
    protected final Object uiDevice;
    //api >= 21
    protected final String METHOD_GET_WINDOW_ROOTS = "getWindowRoots";
    //api >= 17
    protected final String METHOD_GET_DISPLAY_ROTATION = "getDisplayRotation";
    protected final UIAType type;


    public DeviceCore(Object uiDevice, UIAType type) {
        Logger.iFunc(FUNC, "uiDevice:" + uiDevice);
        this.uiDevice = uiDevice;
        this.type = type;
    }

    public DeviceCore(Object uiDevice) {
        Logger.iFunc(FUNC, "uiDevice:" + uiDevice);
        this.uiDevice = uiDevice;
        this.type = SystemUtil.getType();
    }

    //region get roots

    public Object tryGetRoots(int times, long delay) {
        Object obj = null;
        try {
            ReflectionUtil.clearAccessibilityCacheInterval();
            obj = ReflectionUtil.getRoots(uiDevice, METHOD_GET_WINDOW_ROOTS);
        } catch (Exception e) {
            if (e instanceof UIANotConnected && times > 1) {
                UiAutomationCore automation = this.uiAutomatorBridge.getUiAutomation();
                if (automation != null) {
                    automation.disconnect();
                    automation.connect();
                }
                Logger.iFunc(FUNC, String.format("已经第%s次，等待%s(ms),automation:%s", times, delay, automation));
                return tryGetRoots(times - 1, delay * 2);
            }
        }
        return obj;
    }

    //可以在父类被共享
    public synchronized List<AccessibilityNodeInfo> getRoots() {
        List<AccessibilityNodeInfo> ret = new ArrayList<>();
        // Support multi-window searches for API level 21 and up
        if (SystemUtil.API_LEVEL_ACTUAL() >= 21) {
            Object obj = tryGetRoots(3, 1000);
            AccessibilityNodeInfo[] rootArr = (AccessibilityNodeInfo[]) obj;
            if (rootArr == null) {
                getRoot(ret);
                return ret;
            }
            ret.addAll(Arrays.asList(rootArr));
        } else {
            getRoot(ret);
        }
        return ret;
    }

    private void getRoot(List<AccessibilityNodeInfo> resultList) {
        ReflectionUtil.clearAccessibilityCacheInterval();
        AccessibilityNodeInfo root = this.uiAutomatorBridge.getQueryController().getAccessibilityRootNode();
        if (root != null) {
            resultList.add(root);
        }
    }

    //endregion


    /**
     * 获取界面的Rotation
     *
     * @return ROTATION_0(0), ROTATION_90(1), ROTATION_180(2), ROTATION_270(3)
     */
    public int getRotation() {
        int rotation = -1;
        if (SystemUtil.API_LEVEL() >= 17) {
            Object obj = ReflectionUtil.invoke(uiDevice, METHOD_GET_DISPLAY_ROTATION);
            rotation = (int) obj;
        } else {
            Display display = getDisplay();
            rotation = display.getRotation();
        }
        return rotation;
    }

    public UiAutomatorBridge getUiAutomatorBridge() {
        return uiAutomatorBridge;
    }

    // region  click

    /**
     * 用反射实现点击、长按
     *
     * @param x        坐标点x值
     * @param y        坐标点y值
     * @param holdTime 长按时长
     * @return true/false 长按成功/失败
     */
    public boolean click(int x, int y, long holdTime) {
        boolean isDown = uiAutomatorBridge.getInteractionController().touchDown(x, y);
        if (isDown == false) {
            return false;//this.uiDevice.click(x,y);
        }
        SystemClock.sleep(holdTime);
        uiAutomatorBridge.getInteractionController().touchUp(x, y);
        return true;
    }

    public boolean click(int x, int y) {
        boolean isDown = uiAutomatorBridge.getInteractionController().touchDown(x, y);
        if (isDown == false) {
            return false;//this.uiDevice.click(x,y);
        }
        SystemClock.sleep(100);
        uiAutomatorBridge.getInteractionController().touchUp(x, y);
        return true;
    }

    public boolean click(RectInfo rect, ClickPosition position) {
        int[] xy = rect.getPoint(position);
        return click(xy[0], xy[1]);
    }

    public boolean touchDown(final int x, final int y) {
        return uiAutomatorBridge.getInteractionController().touchDown(x, y);
    }

    public boolean touchMove(final int x, final int y) {
        return uiAutomatorBridge.getInteractionController().touchMove(x, y);
    }

    public boolean touchUp(final int x, final int y) {
        return uiAutomatorBridge.getInteractionController().touchUp(x, y);
    }


    //endregion

    public boolean swipe(int startX, int startY, int endX, int endY, int steps) {
        return false;
    }

    //endregion


    //region private method

    private static Display getDisplay() throws UIAException {

        try {
            final Class c = Class.forName("android.view.WindowManagerImpl");
            final Method getInstance = ReflectionUtil.method(c, "getDefault");
            final Object instance = getInstance.invoke(null);

            final Method defaultDisplay = ReflectionUtil.method(instance.getClass(), "getDefaultDisplay");
            return (Display) defaultDisplay.invoke(instance);

        } catch (IllegalAccessException e) {
            Logger.error("Failed to clear Accessibility Node cache. ", e);
        } catch (InvocationTargetException e) {
            Logger.error("Failed to clear Accessibility Node cache. ", e);
        } catch (ClassNotFoundException e) {
            Logger.error("Failed to clear Accessibility Node cache. ", e);
        }
        return null;
    }

    //endregion
}
