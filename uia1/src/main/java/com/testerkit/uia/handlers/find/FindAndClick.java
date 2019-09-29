package com.testerkit.uia.handlers.find;

import com.testerkit.common.exceptions.UIAException;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.model.RectInfo;
import com.testerkit.common.model.UIDumpInfo;
import com.testerkit.common.search.matcher.MatcherManager;
import com.testerkit.common.utils.GsonUtil;
import com.testerkit.common.utils.ReflectionUtil;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.monitor.WatcherManager;
import com.testerkit.uia.requests.IRequest;
import com.testerkit.uia.utils.dumps.XMLHierarchy;

import java.util.ArrayList;
import java.util.List;

public class FindAndClick extends FindEvent {

    public FindAndClick(String mappedUri) {
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
                this.result.setError("没有找到一个元素！！！");
                Logger.iFunc(FUNC, this.result.getError());

                return false;
            }
            if (nodes.size() > 1) {
                this.result.setError("找到多个元素！！！" + GsonUtil.gsonString(nodes));
                Logger.iFunc(FUNC, this.result.getError());
                return false;
            }

            RectInfo rect = nodes.get(0).getRectVisible();
            boolean isOk = BaseContext.getInstance().getDevice().click(rect.centerX(), rect.centerY());
            if (isOk == false) {
                this.result.setError("找到元素但是点击失败！！！");
            }
            return isOk;

        } catch (UIAException e) {
            this.result.setError("查找元素的时候异常！！！" + e.getMessage());
            Logger.error(this.result.getError(), e);
        } finally {

        }
        return false;
    }
}
