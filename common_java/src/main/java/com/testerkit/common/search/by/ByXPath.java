package com.testerkit.common.search.by;

import com.testerkit.common.enums.Attribute;
import com.testerkit.common.enums.ByOption;
import com.testerkit.common.model.UIDumpInfo;
import com.testerkit.common.model.XPathInfo;
import com.testerkit.common.enums.XPathOption;
import com.testerkit.common.utils.StringUtil;
import com.testerkit.common.utils.XmlUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ByXPath extends ByBase {

    private List<XPathInfo> xps;

    public List<XPathInfo> getXps() {
        return xps;
    }

    public ByXPath(List<String> arr) {
        super(arr);
    }

    public ByXPath(List<String> arr, List<XPathInfo> xps) {
        super(arr);
        this.xps = xps;
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
        return Attribute.XPATH;
    }


    @Override
    public boolean isMatchPre() {
        if (this.option == null || this.option == ByOption.IGNORED) {
            return true;
        }
        if(xps == null || xps.isEmpty()){
            return true;
        }
        arr = new ArrayList<String>();
        for (XPathInfo xp:xps) {
            arr.add(xp.getXpath());
        }
        if(arr == null || arr.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "By.xpathList: " + getElementLocator();
    }

}

