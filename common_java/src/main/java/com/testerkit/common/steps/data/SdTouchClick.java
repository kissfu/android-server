package com.testerkit.common.steps.data;

import com.testerkit.common.json.StepJson;
import com.testerkit.common.steps.enums.PointType;

/**
 * @atuthor able
 */
public class SdTouchClick extends StepJson {
    private PointType type = PointType.PERCENT;

    public PointType getType() {
        return type;
    }

    public void setType(PointType type) {
        this.type = type;
    }

    @Override
    public String toDescription() {
        StringBuilder sb = new StringBuilder();
        if (type != null) {
            sb.append("坐标类型:[" + type.getMessage() + "],");
        }
        sb.append(super.toDescription());
        return sb.toString();
    }

    @Override
    public String toString() {
        return "SdTouchClick{" +
                "type=" + type +
                '}';
    }
}
