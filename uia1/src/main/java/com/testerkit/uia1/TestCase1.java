package com.testerkit.uia1;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.testerkit.common.MyClass;
import com.testerkit.common.log.Logger;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.interfaces.ITestCase;
import com.testerkit.uia.log.LogAndroid;
import com.testerkit.uia.servers.socket.NettyServer;
import com.testerkit.uia.utils.SystemUtil;
import com.testerkit.uia.utils.TestCaseUtil;
import com.testerkit.uia1.core.DeviceCore1;

/**
 * Created by able on 2018/9/6.
 */

public class TestCase1 extends UiAutomatorTestCase implements ITestCase {

    TestCaseUtil testCaseUtil = new TestCaseUtil();
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
    protected void tearDown() throws Exception {
        super.tearDown();
        testCaseUtil.tearDown();
    }

    @Override
    public void initCore() {
        Logger.addLog(new LogAndroid());
        BaseContext.getInstance().setTestCase(this);
        BaseContext.getInstance().setDevice(new DeviceCore1(this.getUiDevice(), SystemUtil.getType(1)));

        testCaseUtil.initWatcherConfig();
        testCaseUtil.initSystemApps();
        testCaseUtil.startMonitor();
    }

}
