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

package com.testerkit.uia.handlers.cmd;

import com.testerkit.common.constants.ConstantResult;
import com.testerkit.common.enums.WDStatus;
import com.testerkit.common.json.ResponseJson;
import com.testerkit.common.log.Logger;
import com.testerkit.uia.BaseContext;


public class ExecuteCmd extends ExecuteEvent {

    public ExecuteCmd(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected ResponseJson executeEvent() throws Exception {
        ResponseJson response = new ResponseJson();
        response.setStatus(WDStatus.SUCCESS.code());
        try {
            String cmd = step.getCmd();
            Logger.iFunc(FUNC,cmd);
            String[] arr = cmd.trim().split("-");
            String tag = arr[1].trim();
            if(tag.equalsIgnoreCase("switch")){
                BaseContext.getInstance().getDevice().switchKeyboard(arr[2].trim());
            }
        }catch (Exception e){
            response.setStatus(-1);
            response.setKey(ConstantResult.EXCEPTION_DEVICE);
            response.setValue(e.getMessage());
        }
        return response;
    }
}
