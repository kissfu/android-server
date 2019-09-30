package com.testerkit.common.utils;

import com.testerkit.common.exceptions.UIAException;
import com.testerkit.common.exceptions.UIANotConnected;
import com.testerkit.common.log.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by able on 2018/2/11.
 */

public class ReflectionUtil {
    private static String FUNC = "ReflectionUtil";

    /**
     * Clears the in-process Accessibility cache, removing any stale references. Because the
     * AccessibilityInteractionClient singleton stores copies of AccessibilityNodeInfo instances,
     * calls to public APIs such as `recycle` do not guarantee cached references get updated. See
     * the android.view.accessibility AIC and ANI source code for more information.
     */
    public static boolean clearAccessibilityCache() {
        boolean success = false;

        try {
            final Class c = Class
                    .forName("android.view.accessibility.AccessibilityInteractionClient");
            final Method getInstance = ReflectionUtil.method(c, "getInstance");
            final Object instance = getInstance.invoke(null);
            final Method clearCache = ReflectionUtil.method(instance.getClass(),
                    "clearCache");
            clearCache.invoke(instance);

            success = true;
        } catch (IllegalAccessException e) {
            Logger.error("Failed to clear Accessibility Node cache. ", e);
        } catch (InvocationTargetException e) {
            Logger.error("Failed to clear Accessibility Node cache. ", e);
        } catch (ClassNotFoundException e) {
            Logger.error("Failed to clear Accessibility Node cache. ", e);
        } catch (Exception e) {
            Logger.error("Failed to clear Accessibility Node cache. ", e);
        }
        return success;
    }

    // 上一次清除AccessibilityCache的时间
    // 对于一些webview应用，需要清除这个cache
    // 否则界面发现变化的话（如滑到到页面下方或者出现动态弹框），无法获取变化后的控件信息
    private static long lastAccessibilityCacheClearTime = -1;

    // 个别机型和应用需要删除缓存
    public static void clearAccessibilityCacheInterval() {

        if ((System.currentTimeMillis() - lastAccessibilityCacheClearTime) < 4000) {
            return;
        }

        clearAccessibilityCache();
        Logger.iFunc(FUNC, "clear accessibility cache interval");
        lastAccessibilityCacheClearTime = System.currentTimeMillis();
    }

    private static Pattern patternNotConnected = Pattern.compile("Caused by:.*(UiAutomation not connected!).*");

    public static Object getRoots(Object uiDevice, String method) throws Exception {
        Object obj = null;
        try {
            obj = ReflectionUtil.invoke(uiDevice, method);
        } catch (Exception e) {
            String content = ExceptionUtil.getTrace(e);
            Matcher matcher = patternNotConnected.matcher(content);
            if(matcher.find()){
                System.out.println(matcher.group(1));
                throw  new UIANotConnected(e);
            }
        }
        return null;
    }


    public static Class getClass(final String name) throws UIAException {
        try {
            return Class.forName(name);
        } catch (final ClassNotFoundException e) {
            final String msg = String.format("unable to find class %s", name);
            throw new UIAException(msg, e);
        }
    }

    public static Object getField(final Class clazz, final String fieldName, final Object object) throws UIAException {
        try {
            final Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);

            return field.get(object);
        } catch (final Exception e) {
            final String msg = String.format("error while getting field %s from object %s", fieldName, object);
            Logger.error(msg + " " + e.getMessage());
            throw new UIAException(msg, e);
        }
    }

    public static Object getField(final String field, final Object object) throws UIAException {
        return getField(object.getClass(), field, object);
    }

    public static Object getField(final String className, final String field, final Object object) throws UIAException {
        return getField(getClass(className), field, object);
    }

    public static Object invoke(final Method method, final Object object, final Object... parameters) throws UIAException {
        try {
            // 抑制Java对方法进行检查,主要是针对私有方法而言
            method.setAccessible(true);
            return method.invoke(object, parameters);
        } catch (final Exception e) {
            final String msg = String.format("error while invoking method %s on object %s with parameters %s", method, object, Arrays.toString(parameters));
            Logger.error(msg + " " + e.getMessage());
            throw new UIAException(msg, e);
        }
    }

    public static Object invoke(Object object, String methodName) throws UIAException {
        Method method = method(object, methodName, new Class[]{});
        if (method == null) {
            return null;
        }
        return invoke(method, object, new Object[]{});
    }

    private static Method method(final Object object, final String methodName, final Class... parameterTypes) {
        Method method = null;
        if (object == null) {
            return method;
        }
        Class clazz = object.getClass();
        while (clazz != Object.class) {
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes);
                method.setAccessible(true);
                return method;
            } catch (Exception e) {
                clazz = clazz.getSuperclass();
            }
        }
        return method;
    }

    public static Method method(final Class clazz, final String methodName, final Class... parameterTypes) throws UIAException {
        try {
            final Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true);

            return method;
        } catch (final Exception e) {
            final String msg = String.format("error while getting method %s from class %s with parameter types %s", methodName, clazz, Arrays.toString(parameterTypes));
            Logger.error(msg + " " + e.getMessage());
            throw new UIAException(msg, e);
        }
    }

    public static Method method(final String className, final String method, final Class... parameterTypes) throws UIAException {
        return method(getClass(className), method, parameterTypes);
    }

    public static boolean isMethodExist(Class clazz, String methodName, Class... parameterTypes) {
        try {
            clazz.getMethod(methodName, parameterTypes);
            return true;
        } catch (NoSuchMethodException e) {
            Logger.error("Cannot find method " + methodName, e);
        } catch (SecurityException e) {
            Logger.error("Due to security issue, unable to access method " + methodName, e);
        }

        return false;
    }
}
