package com.testerkit.common.steps.data;

import com.testerkit.common.enums.HandleBy;
import com.testerkit.common.json.StepJson;
import com.testerkit.common.steps.data.variableassign.VariableInfo;
import com.testerkit.common.utils.StringUtil;


/**
 * @atuthor able
 */
public class SdFindInput extends StepJson {

    // 借助 什么处理
    private HandleBy by = HandleBy.NONE;
    private boolean isClear = true; //是否清空
    private VariableInfo variable = new VariableInfo();
    private String value;

    public HandleBy getBy() {
        return by;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public VariableInfo getVariable() {
        return variable;
    }

    public void setVariable(VariableInfo variable) {
        this.variable = variable;
    }

    public boolean isClear() {
        return isClear;
    }

    public void setClear(boolean clear) {
        isClear = clear;
    }

    @Override
    public String toDescription() {
        StringBuilder sb = new StringBuilder();
        if (this.variable != null) {
            sb.append(this.variable.toDescription());
        }
        sb.append(super.toDescription());
        return sb.toString();
    }

    @Override
    public String toString() {
        return "SdFindInput{" +
                "by=" + by +
                ", isClear=" + isClear +
                ", variable=" + variable +
                ", value='" + value + '\'' +
                '}';
    }
}
