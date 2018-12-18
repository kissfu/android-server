package com.testerkit.common.search.by;

public class ByName extends ByBase {
    private final String name;

    public ByName() {
        name = "";
    }

    public ByName(String name) {
        this.name = name;
    }

    @Override
    public boolean isMatch(Object value) {
        String criteria = name;
        if (super.checkCriteria()) {
            return true;
        }
        if (value == null) {
            return false;
        }
        return criteria.equals(value.toString());
    }

    @Override
    public String getElementLocator() {
        return name;
    }

    @Override
    public String toString() {
        return "By.name: " + name;
    }
}
