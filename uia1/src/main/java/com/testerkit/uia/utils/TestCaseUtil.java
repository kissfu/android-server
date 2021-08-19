package com.testerkit.uia.utils;

import com.testerkit.common.constants.PackagesAndroid;
import com.testerkit.common.enums.AppCategory;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.AppInfo;
import com.testerkit.common.utils.StreamUtil;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.monitor.WatcherManager;

import java.io.InputStream;
import java.util.List;

public class TestCaseUtil {

    public void initWatcherConfig() {
        InputStream is = this.getClass().getResourceAsStream("/assets/watcher-config.json");

        String content = "";
        try {
            content = StreamUtil.getStringFromStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e) { }
        }
        Logger.info(content);
    }

    public void initSystemApps() {
        // system app
        List<AppInfo> apps = BaseContext.getInstance().getDevice().getAppList(AppCategory.SYSTEM);
        for (AppInfo a : apps) {
            if (PackagesAndroid.PACKAGES_SYSTEM.contains(a.getPackageName())) {
                continue;
            }
            PackagesAndroid.PACKAGES_SYSTEM.add(a.getPackageName());
        }
    }


    public void startMonitor(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Logger.info("initSystemApps---begin");
                initSystemApps();
                Logger.info("initSystemApps---end");
                Logger.info("WatcherManager---begin");
                WatcherManager.getInstance().start();
                Logger.info("WatcherManager---end");
            }
        }).start();

    }

    public void tearDown(){
        Logger.info("tearDown!!!");
        WatcherManager.getInstance().stop();
    }

}
