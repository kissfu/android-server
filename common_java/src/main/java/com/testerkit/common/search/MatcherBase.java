package com.testerkit.common.search;

import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.model.UIDumpInfo;
import com.testerkit.common.search.by.ByBase;

import java.util.List;

public abstract class MatcherBase<W> {
//    UIDumpInfo dumpInfo = AccessibilityNodeInfoDumper.getUIDumpInfo(AXWindowHelpers.getWindowRoots());


   public List<NodeInfo> findMatches(List<ByBase> bys, UIDumpInfo uiDumpInfo){
       List<NodeInfo> nodes = null;
       //ByXPath byXPath = bys.forEach();


       return nodes;
   }



}
