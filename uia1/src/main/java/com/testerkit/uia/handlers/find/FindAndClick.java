package com.testerkit.uia.handlers.find;

import com.testerkit.common.exceptions.UIAException;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.model.RectInfo;
import com.testerkit.common.model.UIDumpInfo;
import com.testerkit.common.search.matcher.MatcherManager;
import com.testerkit.common.utils.ReflectionUtil;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.monitor.WatcherManager;
import com.testerkit.uia.requests.IRequest;
import com.testerkit.uia.utils.dumps.XMLHierarchy;

public class FindAndClick extends FindEvent {

    public FindAndClick(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected boolean executeFindEvent(IRequest request) throws Exception {


        try {

            long start = System.currentTimeMillis();
            NodeInfo node = null;
            int counter = 0;
            while (System.currentTimeMillis() - start < step.getScroll().getTimeout()) {
                Logger.iFunc(FUNC,"Manager times ",counter++);
                ReflectionUtil.clearAccessibilityCache();
                UIDumpInfo dump = XMLHierarchy.getDumpInfo();
                node = MatcherManager.getInstance().isMatchSingle(step, dump);
                if (node != null) {
                    break;
                }
                WatcherManager.getInstance().runTimes(1);
            }

            if (node == null) {
                Logger.iFunc(FUNC," can not find node!!!");
                return false;
            }
            RectInfo rect = node.getRectVisible();
            return BaseContext.getInstance().getDevice().click(rect.centerX(), rect.centerY());

        } catch (UIAException e) {
            Logger.error("Exception while performing dump SourceNode action: ", e);
        }finally {

        }
        return false;
    }
}
