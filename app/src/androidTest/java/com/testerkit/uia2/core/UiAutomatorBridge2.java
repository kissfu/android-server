package com.testerkit.uia2.core;

import android.app.UiAutomation;
import android.view.Display;
import android.view.InputEvent;

import com.testerkit.uia.core.InteractionController;
import com.testerkit.uia.core.QueryController;
import com.testerkit.uia.core.UiAutomationCore;
import com.testerkit.uia.core.UiAutomatorBridge;
import com.testerkit.common.exceptions.UIAException;

import static com.testerkit.common.utils.ReflectionUtil.getField;
import static com.testerkit.common.utils.ReflectionUtil.invoke;
import static com.testerkit.common.utils.ReflectionUtil.method;

/**
 * Created by able on 2018/2/11.
 */

public class UiAutomatorBridge2 extends UiAutomatorBridge{

    protected static final String FIELD_UI_AUTOMATOR = "mUiAutomation";
    protected static final String METHOD_GET_DEFAULT_DISPLAY = "getDefaultDisplay";
    protected static final String METHOD_INJECT_INPUT_EVENT = "injectInputEvent";


    public UiAutomatorBridge2(Object uiDevice) {
        super(uiDevice);
    }


    //region  extends UiAutomatorBridge

    @Override
    public String CLASS_UI_AUTOMATOR_BRIDGE() {
        return "android.support.test.uiautomator.UiAutomatorBridge";
    }

    @Override
    public InteractionController getInteractionController() throws UIAException {
        return new InteractionController2(getField(CLASS_UI_AUTOMATOR_BRIDGE(), FIELD_INTERACTION_CONTROLLER, uiAutomatorBridge));
    }
    @Override
    public QueryController getQueryController() throws UIAException {
        return new QueryController2(getField(CLASS_UI_AUTOMATOR_BRIDGE(), FIELD_QUERY_CONTROLLER, uiAutomatorBridge));
    }

    @Override
    public UiAutomationCore getUiAutomation() {
        UiAutomation uiAutomation = (UiAutomation) getField(CLASS_UI_AUTOMATOR_BRIDGE(), FIELD_UI_AUTOMATOR, uiAutomatorBridge);
        UiAutomationCore2 uiAutomationCore2 = new UiAutomationCore2(uiAutomation);
        return uiAutomationCore2;
    }

    //endregion



    public Display getDefaultDisplay() throws UIAException {
        return (Display) invoke(method(CLASS_UI_AUTOMATOR_BRIDGE(), METHOD_GET_DEFAULT_DISPLAY), uiAutomatorBridge);
    }

    public boolean injectInputEvent(InputEvent event, boolean sync) throws UIAException {
        return (Boolean) invoke(method(CLASS_UI_AUTOMATOR_BRIDGE(), METHOD_INJECT_INPUT_EVENT, InputEvent.class, boolean.class), uiAutomatorBridge, event, sync);
    }
}
