package com.testerkit.common.search.by;


import com.testerkit.common.enums.Attribute;
import com.testerkit.common.utils.StringUtil;

import java.util.List;

public class ByText extends ByBase {


    private Attribute attribute = Attribute.TEXT;

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
        return this.attribute;
    }

    //有可能是contentDesc / TEXT
    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        String str = getElementLocator();
        if (StringUtil.isEmpty(str)) {
            return "";
        }
        if (attribute == Attribute.TEXT) {
            return "文本: " + str;
        } else if (attribute == Attribute.CONTENT_DESC) {
            return "语音文本: " + str;
        } else {
            return "";
        }
    }
}