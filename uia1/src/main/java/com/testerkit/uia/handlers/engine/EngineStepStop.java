package com.testerkit.uia.handlers.engine;

import com.testerkit.common.enums.WDStatus;
import com.testerkit.common.exceptions.UIAException;
import com.testerkit.common.log.Logger;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.requests.IRequest;
import com.testerkit.uia.requests.http.AppiumResponse;


/**
 * Get page source. Return as string of XML doc
 */
public class EngineStepStop extends EngineEvent {

    public EngineStepStop(String mappedUri) {
        super(mappedUri);
    }

    protected AppiumResponse executeEvent(IRequest request) {
        try {
            BaseContext.getInstance().setStepRunning(false);
        } catch (UIAException e) {
            Logger.error("Exception while EngineStepStop action: ", e);

        }finally {
            Logger.iFunc(FUNC,"EngineStepStop-"+BaseContext.getInstance().isStepRunning());
        }
        return new AppiumResponse(getSessionId(request), WDStatus.NO_RETURN,"no return");
    }
}
