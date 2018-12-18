package com.testerkit.uia.model.serach;

import com.testerkit.uia.utils.dumps.AXWindowHelpers;
import com.testerkit.uia.utils.dumps.AccessibilityNodeInfoDumper;
import com.testerkit.uia.utils.dumps.MyNode;
import com.testerkit.uia.utils.dumps.UIDumpInfo;



/**
 * Created by able on 2018/9/28.
 */

public class ByMatcher {
    private StepInfo step;

    public ByMatcher(StepInfo step) {
        this.step = step;
    }


    public MyNode findMatch(){
        MyNode node = null;

        UIDumpInfo dumpInfo = AccessibilityNodeInfoDumper.getUIDumpInfo(AXWindowHelpers.getWindowRoots());


        for (By by:step.getBy()) {
            //by.isMatch()
        }


        return node;
    }


}
