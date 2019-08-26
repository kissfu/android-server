package com.testerkit.uia.utils;

import com.testerkit.common.enums.ClickPosition;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.RectInfo;
import com.testerkit.uia.BaseContext;

public class ClickUtil {
    public static boolean clickCenter(RectInfo rect){
        return BaseContext.getInstance().getDevice().click(rect, ClickPosition.CENTER);
    }

    /**
     * 偏移左边点击Rect的左边，以Rect 的高一半来衡量偏移量
     *
     * @param rect
     * @return
     */
    public static boolean clickOnLeft(RectInfo rect) {
        if (rect != null) {

            int left = rect.left, top = rect.top, centerY = rect.centerY();
            int xToClick = left - (centerY - top);
            int yToClick = centerY;
            boolean result = BaseContext.getInstance().getDevice().click(xToClick, yToClick);
            return result;
        } else {
            Logger.error(" clickOnLeft RectInfo is null!");
        }
        return false;
    }
}
