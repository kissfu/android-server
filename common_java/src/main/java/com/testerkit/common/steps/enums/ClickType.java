package com.testerkit.common.steps.enums;

/**
 * @atuthor able
 */
public enum ClickType {
    DBCLICK("双击"),
    CLICK("单击");
    private String message;

    ClickType(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
