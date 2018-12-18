package com.testerkit.common.search.by;

public class ByPackageName extends ByBase {
    private final String packageName;

    public ByPackageName() {
        packageName = "";
    }

    public ByPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String getElementLocator() {
        return packageName;
    }

    @Override
    public boolean isMatch(Object value) {
        String criteria = packageName;
        if (super.checkCriteria()) {
            return true;
        }
        if (value == null) {
            return false;
        }

        return criteria.equals(value.toString());
    }

    @Override
    public String toString() {
        return "By.ByPackageName: " + packageName;
    }
}