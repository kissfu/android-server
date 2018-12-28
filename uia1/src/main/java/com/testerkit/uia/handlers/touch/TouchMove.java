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


import com.testerkit.uia.core.UiAutomatorBridge;
import com.testerkit.uia.exceptions.UIAException;
import com.testerkit.common.log.Logger;

public class TouchMove extends TouchEvent {

    public TouchMove(String mappedUri) {
        super(mappedUri);
    }

    @Override
    public boolean executeTouchEvent() throws UIAException {
        Logger.iFunc(FUNC,"TouchMove");
        try {
            int clickX = points.get(0).getX();
            int clickY = points.get(0).getY();
            return UiAutomatorBridge.getInstance().getInteractionController().touchMove(clickX, clickY);
        } catch (Exception e) {
            Logger.error("Problem invoking touchMove: " + e);
            return false;
        }
    }
}
