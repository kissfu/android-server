package com.testerkit.common.steps.data;

import com.testerkit.common.steps.data.variableassign.VariableInfo;
import com.testerkit.common.utils.StringUtil;


/**
 * @atuthor able
 */
public class SdFindSelection extends SdScreenSwipe {


    private VariableInfo variable = new VariableInfo();

    public VariableInfo getVariable() {
        return variable;
    }

    public void setVariable(VariableInfo variable) {
        this.variable = variable;
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
                "variable=" + variable +
                '}';
    }
}
