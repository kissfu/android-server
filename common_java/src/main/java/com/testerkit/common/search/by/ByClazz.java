package com.testerkit.common.search.by;

import com.testerkit.common.utils.ClazzUtil;
import com.testerkit.common.utils.RegExUtil;

public class ByClazz extends ByBase {
    private final String clazz;

    public ByClazz() {
        clazz = "";
    }

    public ByClazz(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean isMatch(Object value) {
        String criteria = clazz;
        if (super.checkCriteria()) {
            return true;
        }
        if (value == null) {
            return false;
        }
        if (criteria.startsWith(RegExUtil.REGULAR) && criteria.endsWith(RegExUtil.REGULAR)) {
            criteria = criteria.substring(1, criteria.lastIndexOf(RegExUtil.REGULAR));
            return RegExUtil.isMatch(criteria, value.toString());

        }
        criteria = ClazzUtil.compatibleRegEx(criteria);
        return RegExUtil.isMatchWithStar(criteria, value.toString());

    }

    @Override
    public String getElementLocator() {
        return clazz;
    }

    @Override
    public String toString() {
        return "By.clazz: " + clazz;
    }
}
