package com.testerkit.uia.handlers.find;

import com.testerkit.common.json.StepJson;
import com.testerkit.uia.handlers.request.SafeRequestHandler;
import com.testerkit.uia.requests.IRequest;
import com.testerkit.uia.requests.http.AppiumResponse;
import com.testerkit.common.enums.WDStatus;
import com.testerkit.common.log.Logger;

public abstract class FindEvent extends SafeRequestHandler {

    public FindEvent(String mappedUri) {
        super(mappedUri);
    }

    protected String FUNC = "FindEvent";
    protected StepJson step;

    @Override
    protected AppiumResponse safeHandle(IRequest request) throws Exception {
        Logger.info("Calling FindEvent... ");

        step = getStep(request);

        if(step == null ||  executeFindEvent(request) == false){
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, String.format(
                    "Cannot generate key find event for FindEvent %s", step));
        }
        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, true);
    }

    protected abstract boolean executeFindEvent(IRequest request) throws Exception;
}
