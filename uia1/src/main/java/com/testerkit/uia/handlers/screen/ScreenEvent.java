/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.testerkit.uia.handlers.screen;

import com.testerkit.common.constants.ConstantResult;
import com.testerkit.common.enums.WDStatus;
import com.testerkit.common.json.ResponseJson;
import com.testerkit.common.json.StepJson;
import com.testerkit.common.log.Logger;
import com.testerkit.uia.handlers.request.SafeRequestHandler;
import com.testerkit.uia.monitor.WatcherManager;
import com.testerkit.uia.requests.IRequest;
import com.testerkit.uia.requests.http.AppiumResponse;


public abstract class ScreenEvent extends SafeRequestHandler {

    protected String FUNC = "ScreenEvent";
    protected StepJson step;

    public ScreenEvent(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IRequest request) throws Exception {
        Logger.iFunc(FUNC, "Calling ScreenEvent... ");
        //记录之前的状态用以还原，暂停监控系统框为长时暂停不能在步骤执行之后被false了。
        boolean status = WatcherManager.getInstance().isPausing();
        try {
            WatcherManager.getInstance().switchPause(true);
            step = getStep(request);
            if (step == null) {
                return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, String.format(
                        "Cannot generate event  for ScreenEvent %s", step), ConstantResult.EXCEPTION_PARAMS);
            }
            return new AppiumResponse(executeEvent(request));
        } catch (Exception e) {
            Logger.error(e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e.getMessage(), ConstantResult.UIA_EXCEPTION);
        } finally {
            WatcherManager.getInstance().switchPause(status);
        }
    }

    protected abstract ResponseJson executeEvent(IRequest request) throws Exception;


}
