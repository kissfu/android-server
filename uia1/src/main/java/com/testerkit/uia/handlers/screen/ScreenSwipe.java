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

import android.graphics.Point;

import com.testerkit.common.constants.ConstantResult;
import com.testerkit.common.enums.ScrollDirection;
import com.testerkit.common.enums.WDStatus;
import com.testerkit.common.json.PointJson;
import com.testerkit.common.json.ResponseJson;
import com.testerkit.common.json.ScrollJson;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.PaddingInfo;
import com.testerkit.common.model.SizeInfo;
import com.testerkit.common.model.SwipeInfo;
import com.testerkit.common.steps.data.SdScreenSwipe;
import com.testerkit.common.steps.data.screenswipe.ScreenSwipeType;
import com.testerkit.common.steps.enums.PointType;
import com.testerkit.common.utils.GsonUtil;
import com.testerkit.common.utils.SleepUtil;
import com.testerkit.common.utils.SwipeUtil;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.model.ScreenSize;
import com.testerkit.uia.requests.IRequest;

import java.util.List;


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
//            while (System.currentTimeMillis() - start < timeout) {
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
//            }

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
            padding = new PaddingInfo(1 / 10f, 1 / 8f, 1 / 10f, 1 / 8f);
        }
        ScreenSize size = BaseContext.getInstance().getDevice().getScreenSize();
        Logger.iFunc(FUNC, padding, ",", size);
        ScrollDirection direction = scroll.getDirection();
        SwipeInfo swipe = SwipeUtil.getByDirection(direction, new SizeInfo(size.getWidth(), size.getHeight()), padding);
        try {
            Logger.iFunc(FUNC, String.format("swipe:%s", swipe));
            for (int i = 0; i < times; i++) {
                boolean isok = BaseContext.getInstance().getDevice().swipe(swipe.getStartX(), swipe.getStartY(), swipe.getEndX(), swipe.getEndY(), swipe.getSteps());
                Logger.iFunc(FUNC, String.format("第%s次滑屏幕，结果:%s。", i, isok));
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
        ScreenSize size = BaseContext.getInstance().getDevice().getScreenSize();
        ResponseJson response = new ResponseJson();
        try {
            ScrollJson scroll = data.getScroll();
            int times = scroll.getTimes();
            long interval = scroll.getInternal();
            List<PointJson> points = data.getPoints();
            int len = points.size();
            Point[] segments = new Point[len];
            PointType type = data.getPointType();
            if (PointType.PERCENT.equals(type)) {
                for (int i = 0; i < len; i++) {
                    segments[i] = new Point((int) (points.get(i).getX() * size.getWidth()), (int) (points.get(i).getY() * size.getHeight()));
                }
            }
            boolean isok = false;
            for (int i = 0; i < times; i++) {
                isok = BaseContext.getInstance().getDevice().swipe(segments, 3);
                Logger.iFunc(FUNC, String.format("坐标滑屏幕，结果:%s。", isok));
                SleepUtil.sleep(interval);
            }
            if (isok) {
                response.setStatus(WDStatus.SUCCESS.code());
            }
        } catch (Exception e) {
            response.setStatus(WDStatus.UNKNOWN_ERROR.code());
            response.setValue(e.getMessage());
            response.setKey(ConstantResult.UIA_EXCEPTION);
            Logger.error(e);
        }

        return response;
    }
}
