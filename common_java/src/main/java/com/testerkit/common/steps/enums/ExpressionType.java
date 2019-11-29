package com.testerkit.common.steps.enums;

/**
 * @atuthor able
 */
public enum ExpressionType {
    ASSIGN("赋值"),
    ASSERT("断言");
    private String message;

    ExpressionType(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
