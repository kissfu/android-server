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

import android.os.SystemClock;


import com.testerkit.uia.core.InteractionController;
import com.testerkit.uia.core.UiAutomatorBridge;
import com.testerkit.uia.exceptions.UIAException;
import com.testerkit.uia.model.serach.PointInfo;
import com.testerkit.uia.utils.Logger;

import org.json.JSONException;


public class TouchLongClick extends TouchEvent {

    public TouchLongClick(String mappedUri) {
        super(mappedUri);
    }

    protected static boolean correctLongClick(final int x, final int y, final long duration) {
        try {
            /*
             * bridge.getClass() returns ShellUiAutomatorBridge on API 18/19 so use
             * the super class.
             */
            InteractionController interactionController = UiAutomatorBridge.getInstance().getInteractionController();
            if (interactionController.touchDown(x, y)) {
                SystemClock.sleep(duration);
                return interactionController.touchUp(x, y);
            }
            return false;
        } catch (final Exception e) {
            Logger.debug("Problem invoking correct long click: " + e);
            return false;
        }
    }

    @Override
    protected boolean executeTouchEvent() throws UIAException {
        PointInfo point = points.get(0);
        long duration = point.getDuration() > 0 ? point.getDuration() : 2000;
        Logger.info(FUNC,"TouchLongClick", duration);
        if (correctLongClick(point.getX(), point.getY(), duration)) {
            return true;
        }

        return false;
    }
}
