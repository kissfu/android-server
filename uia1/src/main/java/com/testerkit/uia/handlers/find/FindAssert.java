package com.testerkit.uia.handlers.find;

import com.testerkit.common.exceptions.UIAException;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.model.UIDumpInfo;
import com.testerkit.common.search.matcher.MatcherManager;
import com.testerkit.common.utils.GsonUtil;
import com.testerkit.common.utils.ReflectionUtil;
import com.testerkit.uia.monitor.WatcherManager;
import com.testerkit.uia.requests.IRequest;
import com.testerkit.uia.utils.dumps.XMLHierarchy;

import java.util.ArrayList;
import java.util.List;

public class FindAssert extends FindEvent {

    public FindAssert(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected boolean executeFindEvent(IRequest request) throws Exception {


        try {

            long start = System.currentTimeMillis();
            List<NodeInfo> nodes = new ArrayList<NodeInfo>();
            int counter = 0;
            while (System.currentTimeMillis() - start < step.getScroll().getTimeout()) {
                Logger.iFunc(FUNC, "find element times ", counter++);
                ReflectionUtil.clearAccessibilityCache();
                UIDumpInfo dump = XMLHierarchy.getDumpInfo();
                nodes = MatcherManager.getInstance().isMatch(step, dump);
                if (nodes.size() > 0) {
                    break;
                }
                WatcherManager.getInstance().runTimes(1);
            }

            if (nodes.size() == 0) {
                this.error = "没有找到一个元素！！！";
                Logger.iFunc(FUNC, this.error);
                return false;
            }
            if (nodes.size() > 0) {
                this.error = "找到多个元素！！！" + GsonUtil.gsonString(nodes);
                Logger.iFunc(FUNC, this.error);
            }

            return true;

        } catch (UIAException e) {
            this.error = "查找元素的时候异常！！！" + e.getMessage();
            Logger.error(this.error, e);
        } finally {

        }
        return false;
    }
}
