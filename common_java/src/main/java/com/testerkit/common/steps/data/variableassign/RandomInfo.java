package com.testerkit.common.steps.data.variableassign;

/**
 * @atuthor able
 */
public class RandomInfo {
   private RandomType type;

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
