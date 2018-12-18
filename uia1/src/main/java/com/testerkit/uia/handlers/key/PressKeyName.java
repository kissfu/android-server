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

package com.testerkit.uia.handlers.key;
import com.testerkit.common.enums.KeyEnum;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.core.DeviceCore;
import com.testerkit.uia.utils.Logger;


public class PressKeyName extends PressEvent {
    public PressKeyName(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected boolean executePressEvent() {
        Logger.info("Calling PressKeyName... ");
        KeyEnum keyEnum = step.getKey().getKeyName();
        boolean isSuccessful = false;
        if(keyEnum == null){
            return isSuccessful;
        }
        DeviceCore core = BaseContext.getInstance().getDevice();
        isSuccessful = core.pressKey(keyEnum.name());
        return isSuccessful;
    }

}
