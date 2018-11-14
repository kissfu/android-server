package com.testerkit.uia.handlers.touch;

import com.testerkit.uia.core.UiAutomatorBridge;
import com.testerkit.uia.exceptions.UIAException;
import com.testerkit.uia.utils.Logger;



public class TouchDown extends TouchEvent {

    public TouchDown(String mappedUri) {
        super(mappedUri);
    }

    @Override
    public boolean executeTouchEvent() throws UIAException {
        Logger.iFunc(FUNC,"TouchDown");
        try {
            int clickX = points.get(0).getX();
            int clickY = points.get(0).getY();
            boolean isTouchDownPerformed = UiAutomatorBridge.getInstance().getInteractionController().touchDown(clickX, clickY);
            return isTouchDownPerformed;
        } catch (Exception e) {
            Logger.error("Problem invoking touchDown: " + e);
            return false;
        }
    }
}
