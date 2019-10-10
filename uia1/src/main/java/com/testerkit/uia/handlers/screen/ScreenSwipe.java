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
import com.testerkit.common.enums.ScrollDirection;
import com.testerkit.common.enums.WDStatus;
import com.testerkit.common.json.ResponseJson;
import com.testerkit.common.json.ScrollJson;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.PaddingInfo;
import com.testerkit.common.steps.data.SdScreenSwipe;
import com.testerkit.common.steps.data.screenswipe.ScreenSwipeType;
import com.testerkit.common.utils.GsonUtil;
import com.testerkit.common.utils.SleepUtil;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.model.ScreenSize;
import com.testerkit.uia.requests.IRequest;


public abstract class ScreenSwipe extends ScreenEvent {

    protected SdScreenSwipe data;

    public ScreenSwipe(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected ResponseJson executeEvent(IRequest request) throws Exception {

        ResponseJson response = new ResponseJson();
        try {
            data = GsonUtil.toBean(this.getStepRaw(request), SdScreenSwipe.class);
            ScrollJson scroll = data.getScroll();
            int timeout = scroll.getTimeout();
            ScreenSwipeType type = data.getType();
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < timeout) {
                switch (type) {
                    case ELEMENT:
                        response = swipeByElement();
                        break;
                    case STANDARD:
                        response = swipeByStandard();
                        break;
                    case POINTS_TWO:
                        response = swipeByPointsTwo();
                        break;
                    case POINTS_MANY:
                        response = swipeByPointsMany();
                        break;
                }
            }

        } catch (Exception e) {
            response.setKey(ConstantResult.UIA_EXCEPTION);
            response.setValue(e.getMessage());
            response.setStatus(WDStatus.UNKNOWN_ERROR.code());
        }
        return response;
    }

    private ResponseJson swipeByElement() {
        return new ResponseJson();
    }

    private ResponseJson swipeByStandard() {
        ResponseJson response = new ResponseJson();
        ScrollJson scroll = data.getScroll();
        int times = scroll.getTimes();
        long interval = scroll.getInternal();
        PaddingInfo padding = data.getPadding();
        if (padding == null) {
            padding = new PaddingInfo(1 / 10, 1 / 10, 1 / 10, 1 / 10);
        }
        ScreenSize size = BaseContext.getInstance().getDevice().getScreenSize();
        Logger.iFunc(FUNC,  padding,size);
        int startX = 0, startY = 0, endX = 0, endY = 0, steps = 10;
        ScrollDirection direction = scroll.getDirection();
        switch (direction) {
            case UP:
                startX = size.getWidth() / 2;
                startY = size.getHeight() - (int) (size.getHeight() * padding.bottom);
                endX = size.getWidth() / 2;
                endY = (int) (size.getHeight() * padding.top);
                break;
            case DOWN:
                startX = size.getWidth() / 2;
                startY = (int) (size.getHeight() * padding.top);
                endX = size.getWidth() / 2;
                endY = size.getHeight() - (int) (size.getHeight() * padding.bottom);
                break;
            case LEFT:
                startX = size.getWidth() - (int) (size.getWidth() * padding.right);
                startY = size.getHeight() / 2;
                endX = (int) (size.getWidth() * padding.left);
                endY = size.getHeight() / 2;
                break;
            case RIGHT:
                startX = (int) (size.getWidth() * padding.left);
                startY = size.getHeight() / 2;
                endX = size.getWidth() - (int) (size.getWidth() * padding.right);
                endY = size.getHeight() / 2;
                break;
        }
        try {
            Logger.iFunc(FUNC, String.format("startX:%s,startY:%s,endX:%s,endY:%s", startX, startY, endX, endY));
            for (int i = 0; i < times; i++) {
                BaseContext.getInstance().getDevice().swipe(startX, startY, endX, endY, steps);
                SleepUtil.sleep(interval);
            }
            response.setStatus(WDStatus.SUCCESS.code());
        } catch (Exception e) {
            response.setStatus(WDStatus.UNKNOWN_ERROR.code());
            response.setValue(e.getMessage());
            response.setKey(ConstantResult.UIA_EXCEPTION);
            Logger.error(e);
        }

        return response;
    }

    private ResponseJson swipeByPointsTwo() {
        return new ResponseJson();
    }

    private ResponseJson swipeByPointsMany() {
        return new ResponseJson();
    }
}
