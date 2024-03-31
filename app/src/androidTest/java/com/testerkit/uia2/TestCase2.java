package com.testerkit.uia2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import com.testerkit.BuildConfig;
import com.testerkit.common.log.Logger;
import com.testerkit.common.utils.Constants;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.interfaces.ITestCase;
import com.testerkit.uia.log.LogAndroid;
import com.testerkit.uia.servers.socket.NettyServer;
import com.testerkit.uia.utils.TestCaseUtil;
import com.testerkit.uia2.core.DeviceCore2;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by able on 2018/9/7.
 */

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class TestCase2 implements ITestCase {

    TestCaseUtil testCaseUtil = new TestCaseUtil();
    public TestCase2() {
        this.initCore();
    }

    @Test
    public void runTest(){

        NettyServer.getInstance().start();
//        startActivity();
//        int counter = 0;
//        while (true){
//            SleepUtils.sleep(2);
//            counter ++ ;
//            Logger.info("","===>"+counter);
//        }
    }

    @After
    public void afterClass(){
        testCaseUtil.tearDown();
    }

    @Override
    public void initCore() {
        Constants.PRO = "2";
        Constants.VERSION = BuildConfig.VERSION_NAME;
        Logger.addLog(new LogAndroid());
        UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        BaseContext.getInstance().setTestCase(this);
        BaseContext.getInstance().setDevice( new DeviceCore2(uiDevice));
        this.startActivity();


        //testCaseUtil.initWatcherConfig();
        //testCaseUtil.initSystemApps();
        testCaseUtil.startMonitor();


//        Handler HANDLER = new Handler(Looper.getMainLooper());
//        HANDLER.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("---beee fps--->");
//                Metronome p = new Metronome();
//                p.setInterval(250);
//                p.addListener(new Audience() {
//                    @Override
//                    public void heartbeat(double fps) {
//                        System.out.println("---fps--->" + fps);
//                    }
//                });
//                p.start();
//                System.out.println("---start fps--->");
//            }
//        }, 1 * 1000);
    }

    public Context getContext(){
        return InstrumentationRegistry.getContext();
    }

    private void startActivity(){
        try {
            Context context = getContext();
            Intent intent = new Intent(); //context.getPackageManager().getLaunchIntentForPackage(packageNameOfYourApp);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            //启动应用
            intent.setComponent(new ComponentName("com.testerkit", "com.testerkit.MainActivity"));
            //starts the app
            context.startActivity(intent);
            Logger.info("","startActivity");
        }catch (Exception e){
            Logger.error(e);
        }
    }


}
