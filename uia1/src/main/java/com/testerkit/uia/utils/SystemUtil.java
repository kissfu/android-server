package com.testerkit.uia.utils;

import android.os.Build;

/**
 * Created by able on 2018/9/6.
 */

public class Constants {
    public static int API_LEVEL(){
        return Build.VERSION.SDK_INT;
    }

    public static int API_LEVEL_ACTUAL(){
        return  Build.VERSION.SDK_INT + ("REL".equals(Build.VERSION.CODENAME) ? 0 : 1);
    }
}
