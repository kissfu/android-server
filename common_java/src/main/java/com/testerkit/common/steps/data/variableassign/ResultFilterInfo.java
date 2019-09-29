package com.testerkit.common.steps.data.variableassign;

/**
 * @atuthor able
 */
public class ResultFilterInfo {
    private ResultFilterType type;
    private String regularExpression;


    public ResultFilterType getType() {
        return type;
    }

    public void setType(ResultFilterType type) {
        this.type = type;
    }

    public String getRegularExpression() {
        return regularExpression;
    }

    public void setRegularExpression(String regularExpression) {
        this.regularExpression = regularExpression;
    }

    @Override
    public String toString() {
        return "ResultFilterInfo{" +
                "type=" + type +
                ", regularExpression='" + regularExpression + '\'' +
                '}';
    }
}
