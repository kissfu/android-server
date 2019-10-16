package com.testerkit.common.utils;

import com.testerkit.common.enums.ScrollDirection;
import com.testerkit.common.model.PaddingInfo;
import com.testerkit.common.model.SizeInfo;
import com.testerkit.common.model.SwipeInfo;

/**
 * @atuthor able
 */
public class SwipeUtil {

    public static ScrollDirection reverseDirection(ScrollDirection direction){
        switch (direction) {
            case UP:
                return ScrollDirection.DOWN;
            case DOWN:
                return ScrollDirection.UP;
            case LEFT:
                return ScrollDirection.RIGHT;
            case RIGHT:
                return ScrollDirection.LEFT;
        }
        return ScrollDirection.NONE;
    }

    public static SwipeInfo getByDirection(ScrollDirection direction, SizeInfo size, PaddingInfo padding) {
        if (padding == null) {
            padding = new PaddingInfo();
        }
        int startX = 0, startY = 0, endX = 0, endY = 0, steps = 10;
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

        return new SwipeInfo(startX, startY, endX, endY, steps);
    }
}
