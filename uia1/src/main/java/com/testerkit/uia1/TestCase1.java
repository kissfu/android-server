package com.testerkit.uia1;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.core.UiAutomatorBridge;
import com.testerkit.uia.interfaces.ITestCase;
import com.testerkit.uia1.core.IDevice1;
import com.testerkit.uia1.core.UiAutomatorBridge1;

/**
 * Created by able on 2018/9/6.
 */

public class TestCase1  extends UiAutomatorTestCase implements ITestCase {

    public TestCase1(){
        this.initCore();
    }


    public void runTest(){

    }

    @Override
    public void initCore() {
        BaseContext.getInstance().setTestCase(this);
        BaseContext.getInstance().setIDevice(new IDevice1(this.getUiDevice()));

        UiAutomatorBridge.setINSTANCE(new UiAutomatorBridge1());
    }
}
