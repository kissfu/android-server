package com.testerkit.uia.handlers.engine;

import com.testerkit.common.enums.WDStatus;
import com.testerkit.common.json.StepJson;
import com.testerkit.common.log.Logger;
import com.testerkit.uia.handlers.request.SafeRequestHandler;
import com.testerkit.uia.requests.IRequest;
import com.testerkit.uia.requests.http.AppiumResponse;

public abstract class EngineEvent extends SafeRequestHandler {
    protected String FUNC = "EngineEvent";
    protected StepJson step;

    public EngineEvent(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IRequest request) throws Exception{
        Logger.iFunc(FUNC,"Calling EngineEvent... ");

        step = getStep(request);
        if(step == null){
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, String.format(
                    "Cannot generate  event for EngineEvent %s", step));
        }


        return  executeEvent(request);
    }

    protected abstract AppiumResponse executeEvent(IRequest request) throws Exception;
}
