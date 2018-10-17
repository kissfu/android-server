package com.testerkit.uia2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import com.testerkit.uia.BaseContext;
import com.testerkit.uia.core.UiAutomatorBridge;
import com.testerkit.uia.interfaces.ITestCase;
import com.testerkit.uia.servers.socket.NettyServer;
import com.testerkit.uia.utils.Constants;
import com.testerkit.uia.utils.Logger;
import com.testerkit.uia.utils.SleepUtils;
import com.testerkit.uia2.core.DeviceCore2;
import com.testerkit.uia2.core.UiAutomatorBridge2;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by able on 2018/9/7.
 */

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class TestCase2 implements ITestCase {

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

    @Override
    public void initCore() {
        Constants.PRO = "2";
        UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        BaseContext.getInstance().setTestCase(this);
        BaseContext.getInstance().setDevice( new DeviceCore2(uiDevice));

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
            intent.setComponent(new ComponentName("com.testerkit.uia2.server", "com.testerkit.uia2.RTCActivity"));
            //starts the app
            context.startActivity(intent);
            Logger.info("","startActivity");
        }catch (Exception e){
            Logger.error(e);
        }
    }
}
