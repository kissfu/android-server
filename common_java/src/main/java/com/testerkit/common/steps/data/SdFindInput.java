package com.testerkit.common.steps.data;

import com.testerkit.common.json.StepJson;
import com.testerkit.common.utils.StringUtil;


/**
 * @atuthor able
 */
public class SdFindInput extends StepJson {

    private String variableName;
    private String value;

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


    @Override
    public String toDescription() {
        StringBuilder sb = new StringBuilder();
        if (StringUtil.isNotEmpty(this.variableName)) {
            sb.append("输入变量:[" + variableName + "],");
        }
        if (StringUtil.isNotEmpty(value)) {
            sb.append("输入内容:[" + value + "],");
        }
        sb.append(super.toDescription());
        return sb.toString();
    }
}
