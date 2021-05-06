package com.testerkit.uia.handlers.engine;

import com.testerkit.common.enums.WDStatus;
import com.testerkit.common.exceptions.UIAException;
import com.testerkit.common.log.Logger;
import com.testerkit.uia.monitor.WatcherManager;
import com.testerkit.uia.requests.IRequest;
import com.testerkit.uia.requests.http.AppiumResponse;


/**
 * Get page source. Return as string of XML doc
 */
public class EngineDialogResume extends EngineEvent {

    public EngineDialogResume(String mappedUri) {
        super(mappedUri);
    }

    protected AppiumResponse executeEvent(IRequest request) {
        try {
            WatcherManager.getInstance().switchAutoClickDialog(true);
            return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS,"ok");
        } catch (UIAException e) {
            Logger.error("Exception while performing dump SourceNode action: ", e);

        }finally {
            Logger.iFunc(FUNC,"EngineDialogResume");
        }
        return new AppiumResponse(getSessionId(request), WDStatus.NO_RETURN,"no return");
    }
}
