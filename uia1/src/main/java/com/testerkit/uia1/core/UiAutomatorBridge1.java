package com.testerkit.uia1.core;

import com.testerkit.uia.core.InteractionController;
import com.testerkit.uia.core.QueryController;
import com.testerkit.uia.core.UiAutomationCore;
import com.testerkit.uia.core.UiAutomatorBridge;
import com.testerkit.common.exceptions.UIAException;

import static com.testerkit.common.utils.ReflectionUtil.getField;
import static com.testerkit.common.utils.ReflectionUtil.method;

/**
 * Created by able on 2018/2/11.
 */

public class UiAutomatorBridge1 extends UiAutomatorBridge{



    public UiAutomatorBridge1(Object uiDevice) {
        super(uiDevice);
    }


    @Override
    public String CLASS_UI_AUTOMATOR_BRIDGE() {
        return "com.android.uiautomator.core.UiAutomatorBridge";
    }

    @Override
    public InteractionController getInteractionController() throws UIAException {
        return new InteractionController1(getField(CLASS_UI_AUTOMATOR_BRIDGE(), FIELD_INTERACTION_CONTROLLER, uiAutomatorBridge));
    }
    @Override
    public QueryController getQueryController() throws UIAException {
        return new QueryController1(getField(CLASS_UI_AUTOMATOR_BRIDGE(), FIELD_QUERY_CONTROLLER, uiAutomatorBridge));
    }

    @Override
    public UiAutomationCore getUiAutomation() {
        return null;
    }

}
