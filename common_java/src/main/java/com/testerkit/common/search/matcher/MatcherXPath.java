package com.testerkit.common.search.matcher;

import com.testerkit.common.enums.XPathOption;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.model.UIDumpInfo;
import com.testerkit.common.model.XPathInfo;
import com.testerkit.common.search.by.ByXPath;
import com.testerkit.common.utils.XmlUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatcherXPath extends MatcherBase<ByXPath, UIDumpInfo> {
    public MatcherXPath(ByXPath by, UIDumpInfo dump) {
        super(by, dump);
    }
//    UIDumpInfo dumpInfo = AccessibilityNodeInfoDumper.getUIDumpInfo(AXWindowHelpers.getWindowRoots());


    @Override
    public boolean isMatch(String value) {

        return true;
    }

    @Override
    public boolean isMatch(NodeInfo node) {
        return false;
    }

    @Override
    public List<NodeInfo> findMatches() {
        List<NodeInfo> nodes = new ArrayList<NodeInfo>();

        if (by == null || by.isIgnoredPre()) {
            return nodes;
        }

        List<XPathInfo> xpathes = by.getXps();
        //升序排列
        Collections.sort(xpathes);

        List<String> arrSimpleXp = null;
        for (XPathInfo xp : xpathes) {
            if (xp.getOption() == XPathOption.SIMPLE) {
                nodes.add(new NodeInfo(xp.getXpath()));
                continue;
            }
            arrSimpleXp = XmlUtil.findByXpath(dump.getUiXml(), xp.getXpath());
            if (arrSimpleXp.size() > 0) {
                break;
            }
        }
        if (arrSimpleXp == null || arrSimpleXp.size() == 0) {
            return nodes;
        }

        for (String xp : arrSimpleXp) {
            nodes.add(new NodeInfo(xp));
        }

        return nodes;
    }

    @Override
    public NodeInfo findMatch() {
        return null;
    }


}
