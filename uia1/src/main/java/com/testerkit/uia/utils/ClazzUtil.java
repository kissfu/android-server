package com.testerkit.uia.utils;

public class ClazzUtil {

    public static String compatibleRegEx(String clazz) {
        String newClazz = "";
        if (StringUtils.isNullOrEmpty(clazz)) {
            return newClazz;
        }
        /**
         * 兼容android.widget.TextView和android.support.v7.widget.AppCompatTextView
         * 兼容android.widget.Button和android.support.v7.widget.AppCompatButton
         * 兼容com.android.internal.policy.impl.PhoneWindow$DecorView和com.android.internal.policy.PhoneWindow$DecorView
         */
        if (clazz.equalsIgnoreCase("android.widget.TextView")
                || clazz.equalsIgnoreCase("android.support.v7.widget.AppCompatTextView")) {
            newClazz = "android*widget*TextView";
        } else if (clazz.equalsIgnoreCase("android.widget.Button")
                || clazz.equalsIgnoreCase("android.support.v7.widget.AppCompatButton")) {
            newClazz = "android*widget*Button";
        } else if (clazz.equalsIgnoreCase("com.android.internal.policy.impl.PhoneWindow$DecorView")
                || clazz.equalsIgnoreCase("com.android.internal.policy.PhoneWindow$DecorView")
                || clazz.equalsIgnoreCase("com.android.internal.policy.MultiPhoneWindow$MultiPhoneDecorView")) {
            newClazz = "com.android.internal.policy*PhoneWindow$*DecorView";
        }

        if (StringUtils.isNullOrEmpty(newClazz)) {
            return clazz;
        }
        return newClazz;
    }

    /**
     * 目前适用xpath解析
     * @param clazz
     * @param isXPath
     * @return
     */
    public static String compatibleSwitch(String clazz,boolean isXPath){
        String newClazz = "";
        if (StringUtils.isNullOrEmpty(clazz)) {
            return newClazz;
        }
        // 有些手机上android.view.ViewGroup 是 android.view.View 如华为手机
        // 或者反过来
        if(isXPath) {
            if (clazz.contains("@class='android.view.ViewGroup'")) {
                newClazz = clazz.replaceAll("@class='android.view.ViewGroup'", "@class='android.view.View'");
            } else if (clazz.contains("@class='android.view.View'")) {
                newClazz = clazz.replaceAll("@class='android.view.View'", "@class='android.view.ViewGroup'");
            }
        }else {
            if (clazz.equalsIgnoreCase("android.view.ViewGroup")) {
                newClazz = "android.view.View";
            } else if (clazz.contains("@class='android.view.View'")) {
                newClazz = "android.view.ViewGroup";
            }
        }

        if (StringUtils.isNullOrEmpty(newClazz)) {
            return clazz;
        }

        return newClazz;
    }
}
