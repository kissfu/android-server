package com.testerkit.common.enums;

public enum StepRule {

    // 手机控制
    NODE("node"),
    CLASS("class"),
    KEY_NAME("key_name"),
    KEY_CODE_LONG("key_code_long"),
    KEY_CODE("key_code"),
    DEFAULT("default"),
    CLICK("click"),
    DOWN("down"),
    MOVE("move"),
    UP("up"),
    NONE("none");


    private final String type;

    private StepRule(String type) {
        this.type = type;
    }

    public String getRule() {
        return type;
    }

    public static StepRule getRule(String type) {
        for (StepRule v : StepRule.values()) {
            if (v.getRule().equals(type)) {
                return v;
            }
        }
        return NONE;
    }
}
