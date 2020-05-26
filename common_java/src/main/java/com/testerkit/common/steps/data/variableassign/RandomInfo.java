package com.testerkit.common.steps.data.variableassign;

/**
 * @atuthor able
 */
public class RandomInfo {
    private RandomType type;

    private RandomCharInfo charInfo;

    public RandomCharInfo getCharInfo() {
        return charInfo;
    }

    public void setCharInfo(RandomCharInfo charInfo) {
        this.charInfo = charInfo;
    }

    public RandomType getType() {
        return type;
    }

    public void setType(RandomType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RandomInfo{" +
                "type=" + type +
                '}';
    }
}
