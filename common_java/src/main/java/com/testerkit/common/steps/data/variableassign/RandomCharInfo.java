package com.testerkit.common.steps.data.variableassign;

import java.util.List;

/**
 * @atuthor able
 */
public class RandomCharInfo {
    private List<RandomCharType> charTypes;
    private int length;
    private String specialChars;

    public List<RandomCharType> getCharTypes() {
        return charTypes;
    }

    public void setCharTypes(List<RandomCharType> charTypes) {
        this.charTypes = charTypes;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getSpecialChars() {
        return specialChars;
    }

    public void setSpecialChars(String specialChars) {
        this.specialChars = specialChars;
    }
}
