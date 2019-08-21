package com.testerkit.uia1;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.testerkit.common.MyClass;
import com.testerkit.common.constants.PackagesAndroid;
import com.testerkit.common.enums.AppCategory;
import com.testerkit.common.model.AppInfo;
import com.testerkit.common.utils.Constants;
import com.testerkit.common.utils.SleepUtil;
import com.testerkit.common.utils.StreamUtil;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.interfaces.ITestCase;
import com.testerkit.uia.log.LogAndroid;
import com.testerkit.uia.servers.socket.NettyServer;
import com.testerkit.common.log.Logger;
import com.testerkit.uia1.core.DeviceCore1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by able on 2018/9/6.
 */

public class TestCase1 extends UiAutomatorTestCase implements ITestCase {

    public TestCase1() {
        super();
        MyClass.ddd();
        //构造方法无法获取this.getUiDevice()
        //this.initCore();
    }


    public void runTest() {
        this.initCore();

        NettyServer.getInstance().start();
//        int i = 0;
//        while (true) {
//            Logger.info("seconds elapsed," + i);
//
//            i++;
//            SleepUtil.sleep(1);
//        }

    }


    @Override
    public void initCore() {
        Logger.addLog(new LogAndroid());
        BaseContext.getInstance().setTestCase(this);
        BaseContext.getInstance().setDevice(new DeviceCore1(this.getUiDevice()));
        this.initWatcherConfig();
        this.initSystemApps();
    }

    private void initWatcherConfig() {
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

    private void initSystemApps() {
        // system app
        List<AppInfo> apps = BaseContext.getInstance().getDevice().getAppList(AppCategory.SYSTEM);
        for (AppInfo a : apps) {
            if (PackagesAndroid.PACKAGES_SYSTEM.contains(a.getPackageName())) {
                continue;
            }
            PackagesAndroid.PACKAGES_SYSTEM.add(a.getPackageName());
        }
    }
}
