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
import com.testerkit.common.steps.enums.PointType;
import com.testerkit.common.utils.NumberUtil;
import com.testerkit.uia.BaseContext;
import com.testerkit.common.exceptions.UIAException;
import com.testerkit.common.log.Logger;
import com.testerkit.uia.model.ScreenSize;


/**
 * 点击 duration：100
 */
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
            return BaseContext.getInstance().getDevice().click(x, y, duration);
//            InteractionController interactionController = UiAutomatorBridge.getInstance().getInteractionController();
//            if (interactionController.touchDown(x, y)) {
//                SystemClock.sleep(duration);
//                return interactionController.touchUp(x, y);
//            }
//            return false;
        } catch (final Exception e) {
            Logger.debug("Problem invoking correct long click: " + e);
            return false;
        }
    }

    private static final long REGULAR_CLICK_LENGTH = 100;//正常点击

    @Override
    protected boolean executeTouchEvent() throws UIAException {
        PointJson point = points.get(0);
        long duration = point.getDuration() > 0 ? point.getDuration() : 2000;
        int clickX = NumberUtil.round(points.get(0).getX());
        int clickY = NumberUtil.round(points.get(0).getY());

        PointType type = this.step.getPointType();
        if (PointType.PERCENT.equals(type)) {
            ScreenSize size = BaseContext.getInstance().getDevice().getScreenSize();
            clickX = (int) (points.get(0).getX() * size.getWidth());
            clickY = (int) (points.get(0).getY() * size.getHeight());
        }

        if (correctLongClick(clickX, clickY, duration)) {
            return true;
        }

        return false;
    }
}
