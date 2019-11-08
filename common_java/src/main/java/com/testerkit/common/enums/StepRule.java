package com.testerkit.common.enums;

public enum StepRule {

    // 手机控制
    NODE("node"),
    CLASS("class"),
    KEY_NAME("key_name"),
    KEY("key"),
    KEY_CODE("key_code"),
    DEFAULT("default"),
    CLICK("click"),
    POINTS("points"),
    DOWN("down"),
    MOVE("move"),
    UP("up"),
    NONE("none"),

    // input
    VALUE("value"),

    //变量操作
    ASSIGN("assign"),

    //screen
    SCREENSHOT("screenshot"),
    SWIPE("swipe"),

    //系统app 接口
    OPERATION("operation"),
    LIST("list"),
    LAUNCH("launch"),
    TERMINATE("terminate"),

    // 下拉框，多个选项，选中一个
    SELECTION("selection"),

    //find元素相关
    INPUT("input"),
    ASSERT("assert");



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
