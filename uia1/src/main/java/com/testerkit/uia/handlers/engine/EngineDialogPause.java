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
public class EngineDialogPause extends EngineEvent {

    public EngineDialogPause(String mappedUri) {
        super(mappedUri);
    }

    protected AppiumResponse executeEvent(IRequest request) {
        try {
            WatcherManager.getInstance().switchAutoClickDialog(false);
            return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS,"ok");
        } catch (UIAException e) {
            Logger.error("Exception while performing dump SourceNode action: ", e);
        }finally {
            Logger.iFunc(FUNC,"EngineDialogPause");
        }
        return new AppiumResponse(getSessionId(request), WDStatus.NO_RETURN,"no return");
    }
}
