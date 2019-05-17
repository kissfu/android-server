package com.testerkit.uia.handlers.app;

import com.testerkit.common.enums.WDStatus;
import com.testerkit.common.json.ResponseJson;
import com.testerkit.common.json.StepJson;
import com.testerkit.common.log.Logger;
import com.testerkit.uia.handlers.request.SafeRequestHandler;
import com.testerkit.uia.requests.IRequest;
import com.testerkit.uia.requests.http.AppiumResponse;

public abstract class AppEvent  extends SafeRequestHandler {
    public AppEvent(String mappedUri) {
        super(mappedUri);
    }

    protected String FUNC = "AppEvent";
    protected StepJson step;

    @Override
    protected AppiumResponse safeHandle(IRequest request) throws Exception {
        Logger.info("Calling PressKey... ");

        step = getStep(request);

        if(step == null ){
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, String.format(
                    "Cannot generate key press event for AppEvent %s", step));
        }

        return  new AppiumResponse(executePressEvent());
    }

    protected abstract ResponseJson executePressEvent() throws Exception;
}
