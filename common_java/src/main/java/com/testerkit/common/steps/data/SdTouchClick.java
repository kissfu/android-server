package com.testerkit.common.steps.data;

import com.testerkit.common.json.StepJson;
import com.testerkit.common.steps.enums.PointType;

/**
 * @atuthor able
 */
public class SdTouchClick extends StepJson {


    @Override
    public String toDescription() {
        StringBuilder sb = new StringBuilder();
        if (this.getPointType() != null) {
            sb.append("坐标类型:[" + this.getPointType().getMessage() + "],");
        }
        sb.append(super.toDescription());
        return sb.toString();
    }

    @Override
    public String toString() {
        return "SdTouchClick{" +
                "type=" + this.getPointType() +
                '}';
    }
}
