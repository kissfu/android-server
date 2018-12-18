package com.testerkit.common.search.by;

import com.testerkit.common.enums.ByOption;
import com.testerkit.common.utils.StringUtil;

public abstract class ByBase {

    protected ByOption option = ByOption.REQUIRED;

    public abstract String getElementLocator();

    public abstract boolean isMatch(Object value);

    protected boolean checkCriteria() {
        if (this.option == null || this.option == ByOption.IGNORED) {
            return true;
        }
        if (StringUtil.isNullOrEmpty(getElementLocator())) {
            return true;
        }
        return false;
    }


    //region Override

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ByBase by = (ByBase) o;

        return toString().equals(by.toString());
    }

    public ByOption getOption() {
        return option;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        // A stub to prevent endless recursion in hashCode()
        return "[unknown locator]";
    }

    //endregion
}
