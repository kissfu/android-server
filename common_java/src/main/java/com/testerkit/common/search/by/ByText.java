package com.testerkit.common.search.by;


import com.testerkit.common.enums.Attribute;

import java.util.List;

public class ByText extends ByBase {


    public ByText(List<String> arr) {
        super(arr);
    }

    @Override
    public List<String> compatibleMode() {
        return arr;
    }

    @Override
    public String compatibleMode(String item) {
        return item;
    }

    @Override
    public Attribute getAttribute() {
        return Attribute.TEXT;
    }

    @Override
    public String toString() {
        return "By.text: " + getElementLocator();
    }
}