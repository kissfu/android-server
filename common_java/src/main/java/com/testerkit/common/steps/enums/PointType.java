package com.testerkit.common.steps.enums;

/**
 * @atuthor able
 */
public enum PointType {
    ORIGINAL("原始坐标"),
    PERCENT("百分比");
    private String message;

    PointType(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
