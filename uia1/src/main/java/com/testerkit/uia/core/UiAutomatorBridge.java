package com.testerkit.uia.core;

import com.testerkit.common.enums.UIAType;
import com.testerkit.common.exceptions.UIAException;
import com.testerkit.common.log.Logger;
import com.testerkit.common.utils.ReflectionUtil;
import com.testerkit.uia.utils.SystemUtil;

import static com.testerkit.common.utils.ReflectionUtil.getField;

/**
 * Created by able on 2018/2/11.
 * 1、通过UiAutomatorBridge.INSTANCE访问
 * 2、通过BaseContext.getDevice().getUiAutomatorBridge() 访问
 */

public abstract class UiAutomatorBridge {


    public abstract String CLASS_UI_AUTOMATOR_BRIDGE();

    protected static final String FIELD_UI_AUTOMATOR_BRIDGE = "mUiAutomationBridge";
    protected static final String FIELD_QUERY_CONTROLLER = "mQueryController";
    protected static final String FIELD_INTERACTION_CONTROLLER = "mInteractionController";

    protected static final String METHOD_AUTOMATOR_BRIDGE = "getAutomatorBridge";
    protected static final String METHOD_QUERY_CONTROLLER = "getQueryController";
    protected final Object uiAutomatorBridge;
    protected final UIAType type;

    private static UiAutomatorBridge INSTANCE;

    public UiAutomatorBridge(Object uiDevice, UIAType type) {
        this.type = type;
        try {
            //uiautomator-v18:2.1.3没有这个属性mUiAutomationBridge
            switch (type) {
                case UIA1_IN_HIGH_LEVEL:
                    this.uiAutomatorBridge = ReflectionUtil.invoke(uiDevice,METHOD_AUTOMATOR_BRIDGE);
                    break;
                default:
                    this.uiAutomatorBridge = getField(FIELD_UI_AUTOMATOR_BRIDGE, uiDevice);
                    break;
            }

        } catch (Error error) {
            Logger.error("ERROR", error);
            throw error;
        } catch (UIAException error) {
            Logger.error("ERROR", error);
            throw new Error(error);
        }
    }

    public UiAutomatorBridge(Object uiDevice) {
        try {
            this.type = SystemUtil.getType();
            //uiautomator-v18:2.1.3没有这个属性mUiAutomationBridge
            this.uiAutomatorBridge = getField(FIELD_UI_AUTOMATOR_BRIDGE, uiDevice);
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

    public abstract InteractionController getInteractionController();

    public abstract QueryController getQueryController();

    public abstract UiAutomationCore getUiAutomation();

}
