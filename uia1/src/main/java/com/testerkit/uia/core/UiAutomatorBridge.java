package com.testerkit.uia.core;

import com.testerkit.uia.BaseContext;
import com.testerkit.uia.exceptions.UIAException;
import com.testerkit.common.log.Logger;

import static com.testerkit.uia.utils.ReflectionUtils.getField;
import static com.testerkit.uia.utils.ReflectionUtils.method;

/**
 * Created by able on 2018/2/11.
 * 1、通过UiAutomatorBridge.INSTANCE访问
 * 2、通过BaseContext.getDevice().getUiAutomatorBridge() 访问
 */

public abstract class UiAutomatorBridge {


    public  abstract  String CLASS_UI_AUTOMATOR_BRIDGE();

    protected static final String FIELD_UI_AUTOMATOR_BRIDGE = "mUiAutomationBridge";
    protected static final String FIELD_QUERY_CONTROLLER = "mQueryController";
    protected static final String FIELD_INTERACTION_CONTROLLER = "mInteractionController";
    protected final Object uiAutomatorBridge;

    private static UiAutomatorBridge INSTANCE ;

    public UiAutomatorBridge(Object uiDevice) {
        try {
            //uiautomator-v18:2.1.3没有这个属性mUiAutomationBridge
            this.uiAutomatorBridge =getField(FIELD_UI_AUTOMATOR_BRIDGE, uiDevice);
        } catch (Error error) {
            Logger.error("ERROR", error);
            throw error;
        } catch (UIAException error) {
            Logger.error("ERROR", error);
            throw new Error(error);
        }
    }


    public static void setINSTANCE(UiAutomatorBridge INSTANCE) {
        UiAutomatorBridge.INSTANCE = INSTANCE;
    }

    public static UiAutomatorBridge getInstance() {
        return INSTANCE;
    }

    public abstract InteractionController getInteractionController() ;

    public abstract QueryController getQueryController() ;

    public abstract UiAutomationCore getUiAutomation();

}
