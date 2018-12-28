package com.testerkit.uia.utils;

import com.testerkit.common.log.Logger;
import com.testerkit.uia.exceptions.UIAException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by able on 2018/2/11.
 */

public class ReflectionUtils {

    /**
     * Clears the in-process Accessibility cache, removing any stale references. Because the
     * AccessibilityInteractionClient singleton stores copies of AccessibilityNodeInfo instances,
     * calls to public APIs such as `recycle` do not guarantee cached references get updated. See
     * the android.view.accessibility AIC and ANI source code for more information.
     */
    public static boolean clearAccessibilityCache() throws UIAException {
        boolean success = false;

        try {
            final Class c = Class
                    .forName("android.view.accessibility.AccessibilityInteractionClient");
            final Method getInstance = ReflectionUtils.method(c, "getInstance");
            final Object instance = getInstance.invoke(null);
            final Method clearCache = ReflectionUtils.method(instance.getClass(),
                    "clearCache");
            clearCache.invoke(instance);

            success = true;
        } catch (IllegalAccessException e) {
            Logger.error("Failed to clear Accessibility Node cache. ", e);
        } catch (InvocationTargetException e) {
            Logger.error("Failed to clear Accessibility Node cache. ", e);
        } catch (ClassNotFoundException e) {
            Logger.error("Failed to clear Accessibility Node cache. ", e);
        }
        return success;
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
            return method.invoke(object, parameters);
        } catch (final Exception e) {
            final String msg = String.format("error while invoking method %s on object %s with parameters %s", method, object, Arrays.toString(parameters));
            Logger.error(msg + " " + e.getMessage());
            throw new UIAException(msg, e);
        }
    }

    public static Object invoke(Object object,String methodName) throws UIAException {
        Method method = method(object,methodName,new Class[]{});
        if(method == null){
            return null;
        }
       return invoke(method,object,new Object[]{});
    }

    private static Method method(final Object object,final String methodName,final Class... parameterTypes) {
        Method method = null;
        if (object == null) {
            return method;
        }
        Class clazz = object.getClass();
        while(clazz != Object.class) {
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
            Logger.error( "Cannot find method " + methodName,e);
        } catch (SecurityException e) {
            Logger.error("Due to security issue, unable to access method " + methodName,e);
        }

        return false;
    }
}
