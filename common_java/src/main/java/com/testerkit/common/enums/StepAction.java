package com.testerkit.common.enums;

public enum StepAction {

    //手机控制
    TOUCH("touch"),
    SOURCE("source"),
    PRESS("press"),
    INPUT("input"),
    FIND("find"),
    NONE("none");



    private final String type;

    private StepAction(String type) {
        this.type = type;
    }

    public String getAction() {
        return type;
    }

    public static StepAction getAction(String type) {
        for (StepAction v : StepAction.values()) {
            if (v.getAction().equals(type)) {
                return v;
            }
        }
        return NONE;
    }
}
