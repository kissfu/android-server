package com.testerkit.common.search.by;

import com.testerkit.common.model.UIDumpInfo;
import com.testerkit.common.model.XPathInfo;
import com.testerkit.common.enums.XPathOption;
import com.testerkit.common.utils.StringUtil;
import com.testerkit.common.utils.XmlUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ByXPath extends ByBase {
    private final List<XPathInfo> xpathes;

    public ByXPath() {
        xpathes = new ArrayList<>();
    }

    public ByXPath(List<XPathInfo> xpathList) {
        this.xpathes = xpathList;
    }

    public List<XPathInfo> getXpathes() {
        return xpathes;
    }

    @Override
    public boolean isMatch(Object value) {
        List<XPathInfo> criteria = xpathes;
        if (super.checkCriteria()) {
            return true;
        }
        //升序排列
        Collections.sort(xpathes);

        UIDumpInfo dumpInfo = null;
        if (value instanceof UIDumpInfo) {
            dumpInfo = (UIDumpInfo) value;
        }


        //TODO xpath match
        for (XPathInfo xp : xpathes) {
            if (xp.getOption() == XPathOption.SIMPLE) {
                continue;
            }
            List<String> arr = XmlUtil.findByXpath(dumpInfo.getUiXml(), xp.getXpath());
            //if(arr.)

        }

        return true;
    }

    @Override
    public String getElementLocator() {
        if (xpathes == null || xpathes.size() == 0) {
            return "";
        }
        return StringUtil.join(xpathes.toArray(), ",");
    }

    @Override
    public String toString() {
        return "By.xpathList: " + getElementLocator();
    }

}

