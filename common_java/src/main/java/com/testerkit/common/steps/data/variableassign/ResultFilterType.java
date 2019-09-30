package com.testerkit.common.steps.data.variableassign;

/**
 * @atuthor able
 */
public enum ResultFilterType {
    NONE("不过滤"),
    ONLY_DIGIT("保留数字"),
    REGULAR_EXPRESSION("正则表达式");

    private String message;

    ResultFilterType(String message){
        this.message = message;
    }
}
