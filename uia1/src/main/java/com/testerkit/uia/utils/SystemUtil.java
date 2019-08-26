package com.testerkit.uia.utils;

import android.os.Build;

import com.testerkit.common.enums.UIAType;

/**
 * Created by able on 2018/9/6.
 */

public class SystemUtil {
    public static int API_LEVEL(){
        return Build.VERSION.SDK_INT;
    }

    public static int API_LEVEL_ACTUAL(){
        return  Build.VERSION.SDK_INT + ("REL".equals(Build.VERSION.CODENAME) ? 0 : 1);
    }

    public static UIAType getType(){
        if(Build.VERSION.SDK_INT < 18){
            return UIAType.UIA1;
        }
        return  UIAType.UIA2;
    }
    public static UIAType getType(int type){
        if(Build.VERSION.SDK_INT < 18){
            return UIAType.UIA1;
        }
        if(type == 1){
            return UIAType.UIA1_IN_HIGH_LEVEL;
        }
        return  UIAType.UIA2;
    }
}
