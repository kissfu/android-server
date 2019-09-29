package com.testerkit.common.steps.data;

import com.testerkit.common.json.StepJson;
import com.testerkit.common.steps.data.variableassign.VariableInitInfo;

/**
 * @atuthor able
 */
public class SdVariableAssign extends StepJson {

    private String variableName;
    private String value;

    private VariableInitInfo init = new VariableInitInfo();

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public VariableInitInfo getInit() {
        return init;
    }

    public void setInit(VariableInitInfo init) {
        this.init = init;
    }

    @Override
    public String toString() {
        return "SdVariableAssign{" +
                "variableName='" + variableName + '\'' +
                ", variableValue='" + value + '\'' +
                ", init=" + init +
                '}';
    }
}
