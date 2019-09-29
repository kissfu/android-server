package com.testerkit.common.steps.data.screenswipe;

/**
 * @atuthor able
 */
public enum ScreenSwipeType {
    ELEMENT("元素滑屏"),
    POINTS_MANY("多点滑屏"),
    POINTS_TWO("两点滑屏"),
    STANDARD("标准滑屏");

    private String message;

    ScreenSwipeType(String message){
        this.message = message;
    }
}
