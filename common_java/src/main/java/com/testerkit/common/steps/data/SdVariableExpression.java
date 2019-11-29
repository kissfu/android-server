package com.testerkit.common.steps.data;

import com.testerkit.common.json.StepJson;
import com.testerkit.common.steps.data.variableassign.VariableInfo;
import com.testerkit.common.steps.enums.ExpressionType;

/**
 * @atuthor able
 */
public class SdVariableExpression extends StepJson {

    private VariableInfo variable = new VariableInfo();

    private ExpressionType expressionType = ExpressionType.ASSIGN;


    public VariableInfo getVariable() {
        return variable;
    }

    public void setVariable(VariableInfo variable) {
        this.variable = variable;
    }

    public ExpressionType getExpressionType() {
        return expressionType;
    }

    public void setExpressionType(ExpressionType expressionType) {
        this.expressionType = expressionType;
    }

    @Override
    public String toDescription() {
        StringBuilder sb = new StringBuilder();
        if (variable != null) {
            sb.append(variable.toDescription());
        }
        sb.append("表达式类型:"+expressionType.getMessage());

        sb.append(super.toDescription());
        return sb.toString();
    }

    @Override
    public String toString() {
        return "SdVariableExpression{" +
                "variable=" + variable +
                ", expressionType=" + expressionType +
                '}';
    }
}
