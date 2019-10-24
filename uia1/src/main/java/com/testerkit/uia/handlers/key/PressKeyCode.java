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

import com.testerkit.common.enums.KeyEnum;
import com.testerkit.common.log.Logger;
import com.testerkit.common.utils.SleepUtil;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.core.DeviceCore;
import com.testerkit.uia.utils.InteractionUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.TimeUnit;


public class PressKeyCode extends PressEvent {
    public PressKeyCode(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected boolean executePressEvent() {
        Logger.iFunc(FUNC, "Calling PressKeyCode... " + step.getKey());
        KeyEnum keyEnum = step.getKey().getKeyName();
        int keyCode = step.getKey().getKeyCode();
        int metaState = step.getKey().getMetaState();
        int flags = step.getKey().getFlags();
        boolean longPress = step.getKey().isLongPress();
        if (keyEnum != null) {
            keyCode = keyEnum.getValue();
        }
        boolean isSuccessful = false;
        if (longPress) {
            isSuccessful = this.longKeyPress(keyCode, flags, metaState);
        } else {
            isSuccessful = this.shortKeyPress(keyCode, flags, metaState);

        }
        return isSuccessful;
    }


    private boolean shortKeyPress(int keyCode, int flags, int metaState) {
        boolean isSuccessful = false;
//        if (SystemUtil.API_LEVEL() < 18) {
        DeviceCore core = BaseContext.getInstance().getDevice();
        isSuccessful = core.pressKey(keyCode, metaState);
        Logger.iFunc(FUNC, "shortKeyPress");
//        } else {
//            flags = flags == -1 ? 0 : flags;
//            metaState = metaState == -1 ? 0 : metaState;
//            long downTime = SystemClock.uptimeMillis();
//            isSuccessful = InteractionUtils.injectEventSync(new KeyEvent(downTime, downTime,
//                    KeyEvent.ACTION_DOWN, keyCode, 0, metaState,
//                    KeyCharacterMap.VIRTUAL_KEYBOARD, 0, flags));
//            isSuccessful &= InteractionUtils.injectEventSync(new KeyEvent(downTime, SystemClock.uptimeMillis(),
//                    KeyEvent.ACTION_UP, keyCode, 0, metaState,
//                    KeyCharacterMap.VIRTUAL_KEYBOARD, 0, flags));
//        }

        return isSuccessful;
    }


    private boolean longKeyPress(int keyCode, int flags, int metaState) {
//        if (SystemUtil.API_LEVEL() >= 18 && monkeyPressLong(keyCode)) {
//            return true;
//        }
        flags = flags == -1 ? 0 : flags;
        metaState = metaState == -1 ? 0 : metaState;
        final long downTime = SystemClock.uptimeMillis();
        boolean isSuccessful = InteractionUtils.injectEventSync(new KeyEvent(downTime, downTime,
                KeyEvent.ACTION_DOWN, keyCode, 0, metaState, KeyCharacterMap.VIRTUAL_KEYBOARD,
                0, flags));
        // https://android.googlesource.com/platform/frameworks/base.git/+/9d83b4783c33f1fafc43f367503e129e5a5047fa%5E%21/#F0
//        isSuccessful &= InteractionUtils.injectEventSync(new KeyEvent(downTime, SystemClock.uptimeMillis(),
//                KeyEvent.ACTION_DOWN, keyCode, 1, metaState, KeyCharacterMap.VIRTUAL_KEYBOARD,
//                0, flags | KeyEvent.FLAG_LONG_PRESS));
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        isSuccessful &= InteractionUtils.injectEventSync(new KeyEvent(downTime, SystemClock.uptimeMillis(),
                KeyEvent.ACTION_UP, keyCode, 0, metaState, KeyCharacterMap.VIRTUAL_KEYBOARD,
                0, flags));

        return isSuccessful;
    }

    /**
     * 权限问题无法执行
     *
     * @param keyCode
     * @return
     */
    private boolean monkeyPressLong(int keyCode) {
        boolean success = false;

        try {

            File file = new File("/data/local/tmp/key.monkey");
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            String monkey = "#Start of Script\r\ntype= user\r\ncount= 49\r\nspeed= 1.0\r\nstart data >>\r\nDispatchKey(0,0,0,%d,0,0,0,0)\r\nUserWait(4000)\r\nDispatchKey(0,0,1,%d,0,0,0,0)";
            monkey = String.format(monkey, keyCode, keyCode);
            output.write(monkey);
            output.flush();
            output.close();

            SleepUtil.sleep(200L);

            Process process = Runtime.getRuntime().exec("monkey -f /data/local/tmp/key.monkey 1");
            process.waitFor();
            SleepUtil.sleep(500L);
            success = true;

        } catch (Exception e) {
            Logger.error(e);
        }

        return success;
    }
}

