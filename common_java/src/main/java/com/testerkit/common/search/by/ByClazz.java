package com.testerkit.common.search.by;

import com.testerkit.common.enums.Attribute;
import com.testerkit.common.utils.ClazzUtil;
import com.testerkit.common.utils.RegExUtil;
import com.testerkit.common.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class ByClazz extends ByBase {

    public ByClazz(List<String> arr) {
        super(arr);
    }

    @Override
    public List<String> compatibleMode() {
        List<String> list = new ArrayList<String>();

        for (String str:arr) {
            list.add(ClazzUtil.compatibleRegEx(str));
        }

        return list;
    }

    @Override
    public String compatibleMode(String item) {
        return ClazzUtil.compatibleRegEx(item);
    }

    @Override
    public Attribute getAttribute() {
        return Attribute.CLASS;
    }


    @Override
    public String toString() {
        return "By.clazz: " + getElementLocator();
    }
}
