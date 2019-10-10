package com.testerkit.common.steps.data;

import com.testerkit.common.json.StepJson;
import com.testerkit.common.model.PaddingInfo;
import com.testerkit.common.steps.data.screenswipe.ScreenSwipeType;

/**
 * @atuthor able
 */
public class SdScreenSwipe extends StepJson {
    private ScreenSwipeType type;
    private PaddingInfo padding;

    public ScreenSwipeType getType() {
        return type;
    }

    public void setType(ScreenSwipeType type) {
        this.type = type;
    }

    public PaddingInfo getPadding() {
        return padding;
    }

    public void setPadding(PaddingInfo padding) {
        this.padding = padding;
    }

    @Override
    public String toDescription() {
        StringBuilder sb = new StringBuilder();
        if (type != null) {
            sb.append("屏幕滑动类型:[" + type.getMessage() + "],");
        }
        sb.append(super.toDescription());
        return sb.toString();
    }

    @Override
    public String toString() {
        return "SdScreenSwipe{" +
                "type=" + type +
                ", padding=" + padding +
                '}';
    }
}
