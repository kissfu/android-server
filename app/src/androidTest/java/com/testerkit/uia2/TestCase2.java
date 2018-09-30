package com.testerkit.uia2;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import com.testerkit.uia.BaseContext;
import com.testerkit.uia.core.UiAutomatorBridge;
import com.testerkit.uia.interfaces.ITestCase;
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

    }

    @Override
    public void initCore() {
        UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        BaseContext.getInstance().setTestCase(this);
        BaseContext.getInstance().setDevice( new DeviceCore2(uiDevice));

    }
}
