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

import android.os.SystemClock;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;

import com.testerkit.uia.BaseContext;
import com.testerkit.uia.core.DeviceCore;
import com.testerkit.uia.handlers.request.SafeRequestHandler;
import com.testerkit.uia.model.KeyEnum;
import com.testerkit.uia.model.serach.StepInfo;
import com.testerkit.uia.requests.IRequest;
import com.testerkit.uia.requests.http.AppiumResponse;
import com.testerkit.uia.requests.socket.ISocketRequest;
import com.testerkit.uia.servers.WDStatus;
import com.testerkit.uia.utils.InteractionUtils;
import com.testerkit.uia.utils.Logger;
import com.testerkit.uia.utils.SleepUtils;


public class PressKeyCode extends PressEvent {
    public PressKeyCode(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected boolean executePressEvent() {
        Logger.info("Calling PressKeyCode... ");
        final int keyCode = step.getKey().getKeyCode();
        Integer metaState = step.getKey().getMetaState();
        Integer flags = step.getKey().getFlags();
        boolean isSuccessful = false;
        if (flags == -1) {
            DeviceCore core = BaseContext.getInstance().getDevice();
            if (metaState == -1) {
                isSuccessful = core.pressKey(keyCode);
            } else {
                isSuccessful = core.pressKey(keyCode, metaState);
            }
        } else {
            flags = flags == -1 ? 0 : flags;
            metaState = metaState == -1 ? 0 : metaState;
            long downTime = SystemClock.uptimeMillis();
            isSuccessful = InteractionUtils.injectEventSync(new KeyEvent(downTime, downTime,
                    KeyEvent.ACTION_DOWN, keyCode, 0, metaState,
                    KeyCharacterMap.VIRTUAL_KEYBOARD, 0, flags));
            isSuccessful &= InteractionUtils.injectEventSync(new KeyEvent(downTime, SystemClock.uptimeMillis(),
                    KeyEvent.ACTION_UP, keyCode, 0, metaState,
                    KeyCharacterMap.VIRTUAL_KEYBOARD, 0, flags));
        }
        return isSuccessful;
    }

}
