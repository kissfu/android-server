package com.testerkit.common.enums;

public enum StepType {

    UIA("uia"),
    X5("x5"),
    WEB("web"),
    DEVICE("device"),
    NONE("none");


    private final String type;

    private StepType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static StepType getRule(String type) {
        for (StepType v : StepType.values()) {
            if (v.getType().equals(type)) {
                return v;
            }
        }
        return NONE;
    }
}
