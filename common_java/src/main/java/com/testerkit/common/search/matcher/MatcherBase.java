package com.testerkit.common.search.matcher;

import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.model.UIDumpInfo;
import com.testerkit.common.search.by.ByBase;

import java.util.List;

public abstract class MatcherBase<B, W> {
//    UIDumpInfo dumpInfo = AccessibilityNodeInfoDumper.getUIDumpInfo(AXWindowHelpers.getWindowRoots());

    protected W dump;
    protected B by;


    public MatcherBase(B by, W dump) {
        this.dump = dump;
        this.by = by;
    }

    public abstract boolean isMatch(String value);
    public abstract boolean isMatch(NodeInfo node);

    public abstract List<NodeInfo> findMatches();

    public abstract NodeInfo findMatch();

}
