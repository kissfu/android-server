package com.testerkit.common.search.by;

import com.testerkit.common.enums.Attribute;

import java.util.List;

public class ByName extends ByBase {


    public ByName(List<String> arr) {
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
        return Attribute.NAME;
    }

    @Override
    public String toString() {
        return "By.name: " + getElementLocator();
    }
}
