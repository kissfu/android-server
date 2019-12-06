package com.testerkit.common.steps.data;

import com.testerkit.common.enums.HandleBy;
import com.testerkit.common.steps.data.variableassign.VariableInfo;


/**
 * @atuthor able
 */
public class SdFindSelection extends SdScreenSwipe {

    // 借助 什么处理
    private HandleBy by = HandleBy.NONE;
    private VariableInfo variable = new VariableInfo();
    private int arrIndex;
    private int arrType;//下拉框的类型，参考配置文件
    private int eleType;//下拉框元素的类型，1中间元素，0大框元素


    public HandleBy getBy() {
        return by;
    }

    public void setBy(HandleBy by) {
        this.by = by;
    }

    public VariableInfo getVariable() {
        return variable;
    }

    public void setVariable(VariableInfo variable) {
        this.variable = variable;
    }

    public int getArrIndex() {
        return arrIndex;
    }

    public void setArrIndex(int arrIndex) {
        this.arrIndex = arrIndex;
    }

    public int getArrType() {
        return arrType;
    }

    public void setArrType(int arrType) {
        this.arrType = arrType;
    }

    public int getEleType() {
        return eleType;
    }

    public void setEleType(int eleType) {
        this.eleType = eleType;
    }

    @Override
    public String toDescription() {
        StringBuilder sb = new StringBuilder();
        if (variable != null) {
            sb.append(variable.toDescription());
        }
        sb.append(super.toDescription());
        return sb.toString();
    }

    @Override
    public String toString() {
        return "SdFindSelection{" +
                "by=" + by +
                ", variable=" + variable +
                '}';
    }
}
