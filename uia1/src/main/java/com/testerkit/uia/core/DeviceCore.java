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
            Display display = this.getDisplay();
            if (display != null) {
                rotation = display.getRotation();
            }
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

    // region swipe

    public boolean swipe(int startX, int startY, int endX, int endY, int steps) {
        return false;
    }

    // endregion

    //region screen

    // 尽量在core里面进行sdk版本的兼容判断
    public ScreenSize getScreenSize() {
        ScreenSize size = this.getDisplaySize();
        if (SystemUtil.API_LEVEL() >= 24) {
            size = this.handleScreenPixelByDp(size, this.getDisplaySizeDp());
        }
        return size;
    }

    public abstract ScreenSize getDisplaySize();

    public abstract ScreenSize getDisplaySizeDp();

    /**
     * 通过设备独立像素计算出设备的实际分辨率
     *
     * @param displaySize   通过UIAutomator获取的逻辑分辨率宽,通过UIAutomator获取的逻辑分辨率高
     * @param displaySizeDp 通过UIAutomator获取的设备独立分辨率宽和高
     * @return 返回相对应的宽和高
     */
    private ScreenSize handleScreenPixelByDp(ScreenSize displaySize, ScreenSize displaySizeDp) {

        int displayWidth = displaySize.getWidth(), displayHeight = displaySize.getHeight();
        // 手机实际分辨率宽和高
        int screenWidth = 0;
        int screenHeight = 0;

        // 获取独立分辨率宽高
        int screenDpWidth = displaySizeDp.getWidth();
        int screenDpHeight = displaySizeDp.getHeight();

        // 分别通过宽和高算出设备像素比
        double dpr_a = displayWidth / (double) screenDpWidth;
        double dpr_b = displayHeight / (double) screenDpHeight;

        if (Math.abs(dpr_a - dpr_b) >= 0.02) {
            // 通过对比，谁的设备像素比低，相对应的宽或者高通过UIAutomator获取的不正确
            if (dpr_a > dpr_b) {
                screenHeight = (int) (dpr_a * screenDpHeight);
                // 如果double类型数据无限多，算出来的设备分辨率高就会少，就需要算出个位数补到十
                int unitNum = screenHeight % 10;
                if (unitNum != 0) {
                    screenHeight = screenHeight + (10 - unitNum);
                }
                screenWidth = displayWidth;
                // Log.e(Utils.tag, "UIAutomator getDisplayHeight is error! The correct height is： " + screenHeight);
            } else {
                screenWidth = (int) (dpr_b * screenDpWidth);
                int unitNum = screenWidth % 10;
                if (unitNum != 0) {
                    screenWidth = screenWidth + (10 - unitNum);
                }
                screenHeight = displayHeight;
                // Log.e(Utils.tag, "UIAutomator getDisplayWidth is error! The correct width is： " + screenWidth);
            }

        } else {
            screenWidth = displayWidth;
            screenHeight = displayHeight;
        }

        // 如果是获取设备真实分辨率宽就返回设备真实分辨率宽
        return new ScreenSize(screenWidth, screenDpWidth);

    }

    //endregion

    //endregion


    //region private method

    private Display getDisplay() throws UIAException {

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
