package com.testerkit.uia.handlers.cmd;

import com.testerkit.common.enums.WDStatus;
import com.testerkit.common.json.ResponseJson;
import com.testerkit.common.log.Logger;
import com.testerkit.common.utils.StringUtil;
import com.testerkit.interfaces.idevice.iscript.StepDataBase;
import com.testerkit.uia.handlers.request.SafeRequestHandler;
import com.testerkit.uia.requests.IRequest;
import com.testerkit.uia.requests.http.AppiumResponse;

public abstract class ExecuteEvent extends SafeRequestHandler {

    public ExecuteEvent(String mappedUri) {
        super(mappedUri);
    }

    protected String FUNC = "ExecuteEvent";
    protected StepDataBase step;

    @Override
    protected AppiumResponse safeHandle(IRequest request) throws Exception {
        Logger.info("Calling ExecuteEvent... ");

        step = getStep(request);

        if(step == null || StringUtil.isEmpty(step.getCmd())){
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, String.format(
                    "Cannot generate execute event for event %s", step));
        }
        ResponseJson responseJson = executeEvent();
        responseJson.setSessionId(getSessionId(request));
        return new AppiumResponse(responseJson);
    }

    protected abstract ResponseJson executeEvent() throws Exception;
}
