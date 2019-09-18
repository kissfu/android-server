package com.testerkit.common.steps.data;

import com.testerkit.common.json.StepJson;


/**
 * @atuthor able
 */
public class SdFindAssert extends StepJson {

    private boolean exist;

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    @Override
    public String toDescriptionn() {
        StringBuilder sb = new StringBuilder();
        String strExist = this.exist ? "存在" : "不存在";
        sb.append("验证元素:[" + strExist + "],");
        sb.append(super.toDescriptionn());
        return sb.toString();
    }
}
