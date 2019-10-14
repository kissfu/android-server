package com.testerkit.common.steps.data.variableassign;

import com.testerkit.common.utils.StringUtil;

/**
 * @atuthor able
 */
public class VariableInfo {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public String toDescription() {
        StringBuilder sb = new StringBuilder();
        if (StringUtil.isNotEmpty(this.name)) {
            sb.append("输入变量:[" + name + "],");
        }
        if (StringUtil.isNotEmpty(value)) {
            sb.append("输入内容:[" + value + "],");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "VariableInfo{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
