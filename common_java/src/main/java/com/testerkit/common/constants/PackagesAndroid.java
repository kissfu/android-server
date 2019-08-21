package com.testerkit.common.constants;

import java.util.ArrayList;
import java.util.List;

public class PackagesAndroid {
    public static List<String> PACKAGES_SYSTEM= new ArrayList<String>();
    // 不希望自动处理的包名
    public static List<String> PACKAGES_IGNORE = new ArrayList<String>();
    /**
     * 需要被杀死的包名
     */
    public static List<String> PACKAGES_NEED_KILL = new ArrayList<String>();



    //TOP15
    static {
        PACKAGES_SYSTEM.add("android");// android/com.android.internal.app.ResolverActivity//android/com.vivo.services.security.client.VivoPermissionActivity
        PACKAGES_SYSTEM.add("com.android.settings");// com.android.settings/.bluetooth.RequestPermissionHelperActivity
        PACKAGES_SYSTEM.add("com.sec.android.app.launcher");// com.sec.android.app.launcher/com.android.launcher2.Launcher
        PACKAGES_SYSTEM.add("com.android.launcher");// com.android.launcher/com.android.launcher2.Launcher
        PACKAGES_SYSTEM.add("com.android.browser");// com.android.browser/.BrowserActivity
        PACKAGES_SYSTEM.add("com.android.packageinstaller");// com.android.packageinstaller/.PackageInstallerActivity
        PACKAGES_SYSTEM.add("com.yulong.android.seccenter");// com.yulong.android.seccenter/.dataprotection.ui.PermAlertActivity
        PACKAGES_SYSTEM.add("com.lenovo.launcher");// com.lenovo.launcher/com.lenovo.launcher2.Launcher
        PACKAGES_SYSTEM.add("com.htc.launcher");// com.htc.launcher/.Launcher
        PACKAGES_SYSTEM.add("com.huawei.android.launcher");// com.huawei.android.launcher/.Launcher
        PACKAGES_SYSTEM.add("com.yulong.android.launcher3");// com.yulong.android.launcher3/.Launcher
        PACKAGES_SYSTEM.add("com.oppo.launcher");// com.oppo.launcher/.Launcher
        PACKAGES_SYSTEM.add("com.sec.android.app.twlauncher");// com.sec.android.app.twlauncher/.Launcher
        PACKAGES_SYSTEM.add("com.miui.home");// com.miui.home/.launcher.Launcher
        PACKAGES_SYSTEM.add("com.sonyericsson.home");// com.sonyericsson.home/.HomeActivityr
        PACKAGES_SYSTEM.add("com.gionee.anti.stolen");
        PACKAGES_SYSTEM.add("com.huawei.systemmanager");
        PACKAGES_SYSTEM.add("com.gionee.gnservice");
        PACKAGES_SYSTEM.add("com.android.music");
        PACKAGES_SYSTEM.add("com.ct.client");
        PACKAGES_SYSTEM.add("com.samsung.android.app.headlines");
        PACKAGES_SYSTEM.add("com.qihoo360.mobilesafe");
        PACKAGES_SYSTEM.add("com.mediatek.batterywarning");
        PACKAGES_SYSTEM.add("com.sec.android.app.capabilitymanager");
        PACKAGES_SYSTEM.add("com.lenovo.safecenter");
        PACKAGES_SYSTEM.add("com.cleanmaster.mguard_cn");
        PACKAGES_SYSTEM.add("com.coloros.safecenter");
        PACKAGES_SYSTEM.add("com.android.systemui"); // 锤子手机
        PACKAGES_SYSTEM.add("com.lbe.security.miui"); // 红米5 Plus

        // 不希望自动处理的包，现在只有乐视
        PACKAGES_IGNORE.add("com.ivvi.storeApp");
        PACKAGES_IGNORE.add("com.ivvi.android.appstore");
        PACKAGES_IGNORE.add("com.letv.tvos.sport");
        PACKAGES_IGNORE.add("com.tvos.storemusic");
        PACKAGES_IGNORE.add("com.letv.tvos.sport");
        PACKAGES_IGNORE.add("com.letv.app.appstore");
        PACKAGES_IGNORE.add("com.letv.games");
        PACKAGES_IGNORE.add("com.letv.tvos.appstore");
        PACKAGES_IGNORE.add("com.letv.tvos.gamecenter");

        // 关于海信电视
        // 聚好看应用
        PACKAGES_IGNORE.add("com.jamdeo.tv.vod");
        PACKAGES_IGNORE.add("com.tencent.qqmusictv");

        //所有手机联系人/相机/相册/闹铃 界面
        PACKAGES_IGNORE.add("com.android.contacts");
        PACKAGES_IGNORE.add("com.android.camera");
        PACKAGES_IGNORE.add("com.android.gallery3d");
        PACKAGES_IGNORE.add("com.android.deskclock");

        // 忽略锤子手机桌面
        PACKAGES_IGNORE.add("com.android.launcher3");

        // 希望被杀死的包名
        PACKAGES_NEED_KILL.add("com.coloros.securepay");
    }


}
