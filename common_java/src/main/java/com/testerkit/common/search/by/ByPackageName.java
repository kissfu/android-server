package com.testerkit.common.search.by;

import com.testerkit.common.enums.Attribute;
import com.testerkit.common.utils.StringUtil;

import java.util.List;

public class ByPackageName extends ByBase {


    public ByPackageName(List<String> arr) {
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
        return Attribute.PACKAGE;
    }

    @Override
    public String toString() {
        String str = getElementLocator();
        if(StringUtil.isEmpty(str)){
            return "";
        }
        return "包名: " + str;
    }
}