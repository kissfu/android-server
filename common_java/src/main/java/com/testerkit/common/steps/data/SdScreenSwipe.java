package com.testerkit.common.steps.data;

import com.testerkit.common.enums.ScrollDirection;
import com.testerkit.common.json.StepJson;
import com.testerkit.common.steps.data.screenswipe.ScreenSwipeType;

/**
 * @atuthor able
 */
public class SdScreenSwipe extends StepJson {
    private ScreenSwipeType type;

    public ScreenSwipeType getType() {
        return type;
    }

    public void setType(ScreenSwipeType type) {
        this.type = type;
    }

}
