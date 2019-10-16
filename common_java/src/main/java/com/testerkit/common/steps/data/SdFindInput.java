package com.testerkit.common.steps.data;

import com.testerkit.common.enums.HandleBy;
import com.testerkit.common.json.StepJson;
import com.testerkit.common.utils.StringUtil;


/**
 * @atuthor able
 */
public class SdFindInput extends StepJson {

    // 借助 什么处理
    private HandleBy by = HandleBy.NONE;
    private boolean isClear = true; //是否清空
    private String variableName;
    private String value;

    public HandleBy getBy() {
        return by;
    }

    public void setBy(HandleBy by) {
        this.by = by;
    }

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

    public boolean isClear() {
        return isClear;
    }

    public void setClear(boolean clear) {
        isClear = clear;
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

    @Override
    public String toString() {
        return "SdFindInput{" +
                "isClear=" + isClear +
                ", variableName='" + variableName + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
