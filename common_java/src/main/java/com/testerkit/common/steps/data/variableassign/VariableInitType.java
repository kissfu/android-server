package com.testerkit.common.steps.data.variableassign;

/**
 * @atuthor able
 */
public enum VariableInitType {
    DB("数据库"),
    API("接口"),
    ELEMENT("元素"),
    RANDOM("随机数");

    private String message;

    VariableInitType(String message){
        this.message = message;
    }
}
