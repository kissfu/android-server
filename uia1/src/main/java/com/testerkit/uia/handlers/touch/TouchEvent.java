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

package com.testerkit.uia.handlers.touch;

import com.testerkit.common.json.PointJson;
import com.testerkit.common.json.StepJson;
import com.testerkit.uia.handlers.request.SafeRequestHandler;
import com.testerkit.uia.requests.IRequest;
import com.testerkit.uia.requests.http.AppiumResponse;
import com.testerkit.uia.requests.socket.ISocketRequest;
import com.testerkit.common.enums.WDStatus;

import java.util.List;

public abstract class TouchEvent extends SafeRequestHandler {

    protected String FUNC = "Touch";
    protected List<PointJson> points;
    protected StepJson step;

    public TouchEvent(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IRequest request) throws Exception{

        if(request instanceof  ISocketRequest){
            ISocketRequest socketRequest = (ISocketRequest)request;
            this.step =  socketRequest.getStepInfo();
            this.points = this.step.getPoints();
        }

        if(points == null || points.size() == 0 || executeTouchEvent() == false){
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, false);
        }
        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, true);
    }

    protected abstract boolean executeTouchEvent() throws Exception;


}
