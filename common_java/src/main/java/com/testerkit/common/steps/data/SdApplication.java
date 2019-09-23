package com.testerkit.common.steps.data;


import com.testerkit.common.model.AppInfo;
import com.testerkit.common.json.StepJson;

/**
 * @atuthor able
 */
public class SdApplication extends StepJson {
    private AppInfo app;

    public AppInfo getApp() {
        return app;
    }

    public void setApp(AppInfo app) {
        this.app = app;
    }


    @Override
    public String toString() {
        return "SdApplication{" +
                "app=" + app +
                '}';
    }
}
