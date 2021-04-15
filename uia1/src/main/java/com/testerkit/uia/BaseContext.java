package com.testerkit.uia;

import com.testerkit.uia.core.DeviceCore;
import com.testerkit.uia.interfaces.ITestCase;

/**
 * Created by able on 2018/2/14.
 */

public class BaseContext {

    volatile boolean stepRunning = true;

    DeviceCore device;

    ITestCase testCase;

    public boolean isStepRunning() {
        return stepRunning;
    }

    public void setStepRunning(boolean stepRunning) {
        this.stepRunning = stepRunning;
    }

    public DeviceCore getDevice() {
        return device;
    }

    public void setDevice(DeviceCore device) {
        this.device = device;
    }

    public ITestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(ITestCase testCase) {
        this.testCase = testCase;
    }


    private static class SingletonHolder {
        public final static BaseContext instance = new BaseContext();
    }

    public static BaseContext getInstance()
    {
        return SingletonHolder.instance;
    }
}
