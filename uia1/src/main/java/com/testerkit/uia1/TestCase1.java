package com.testerkit.uia1;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.interfaces.ITestCase;
import com.testerkit.uia.utils.Logger;
import com.testerkit.uia.utils.SleepUtils;
import com.testerkit.uia1.core.DeviceCore1;

/**
 * Created by able on 2018/9/6.
 */

public class TestCase1  extends UiAutomatorTestCase implements ITestCase {

    public TestCase1(){
        this.initCore();
    }


    public void runTest(){
        int i=0;
        while (true){
            Logger.info("seconds elapsed,"+i);
            i++;
            SleepUtils.sleep(1);
        }

    }



    @Override
    public void initCore() {
        BaseContext.getInstance().setTestCase(this);
        BaseContext.getInstance().setDevice(new DeviceCore1(this.getUiDevice()));
    }
}
