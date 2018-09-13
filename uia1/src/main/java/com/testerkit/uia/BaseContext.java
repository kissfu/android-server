package com.testerkit.uia;

import com.testerkit.uia.interfaces.IDevice;
import com.testerkit.uia.interfaces.ITestCase;

/**
 * Created by able on 2018/2/14.
 */

public class BaseContext {

    IDevice IDevice;

    ITestCase testCase;

    public IDevice getIDevice() {
        return IDevice;
    }

    public void setIDevice(IDevice IDevice) {
        this.IDevice = IDevice;
    }

    public ITestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(ITestCase testCase) {
        this.testCase = testCase;
    }


    private static class SingletonHolder
    {
        public final static BaseContext instance = new BaseContext();
    }
    public static BaseContext getInstance()
    {
        return SingletonHolder.instance;
    }
}
