package com.testerkit.uia.handlers.find;

import com.testerkit.common.constants.ConstantResult;
import com.testerkit.common.enums.WDStatus;
import com.testerkit.common.json.StepJson;
import com.testerkit.common.log.Logger;
import com.testerkit.uia.handlers.request.SafeRequestHandler;
import com.testerkit.uia.monitor.WatcherManager;
import com.testerkit.uia.requests.IRequest;
import com.testerkit.uia.requests.http.AppiumResponse;

public abstract class FindEvent extends SafeRequestHandler {

    public FindEvent(String mappedUri) {
        super(mappedUri);
    }

    protected String FUNC = "FindEvent";
    protected StepJson step;
    protected String error = "";

    @Override
    protected AppiumResponse safeHandle(IRequest request) throws Exception {
        Logger.info("Calling FindEvent... ");

        //记录之前的状态用以还原，暂停监控系统框为长时暂停不能在步骤执行之后被false了。
        boolean status = WatcherManager.getInstance().isPausing();
        try {
            step = getStep(request);
            WatcherManager.getInstance().switchPause(true);
            if (step == null || executeFindEvent(request) == false) {
                return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, String.format(
                        "%s-->%s", error, step), ConstantResult.NO_FIND_ELEMENT);
            }
        } catch (Exception e) {
        } finally {
            WatcherManager.getInstance().switchPause(status);
        }


        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, true);
    }

    protected abstract boolean executeFindEvent(IRequest request) throws Exception;
}
