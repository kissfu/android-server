package com.testerkit.common.search.by;

import com.testerkit.common.utils.RegExUtil;

public class ByText extends ByBase {
    private final String text;

    public ByText() {
        text = "";
    }

    public ByText(String text) {
        this.text = text;
    }


    @Override
    public String getElementLocator() {
        return text;
    }

    @Override
    public boolean isMatch(Object value) {
        String criteria = text;
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
        return RegExUtil.isMatchWithStar(criteria, value.toString());
    }

    @Override
    public String toString() {
        return "By.text: " + text;
    }
}