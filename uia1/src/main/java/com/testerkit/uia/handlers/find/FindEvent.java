package com.testerkit.uia.handlers.find;

import com.testerkit.common.constants.ConstantResult;
import com.testerkit.common.enums.WDStatus;
import com.testerkit.common.exceptions.UIAException;
import com.testerkit.common.json.StepJson;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.model.UIDumpInfo;
import com.testerkit.common.search.matcher.MatcherManager;
import com.testerkit.common.utils.GsonUtil;
import com.testerkit.common.utils.ReflectionUtil;
import com.testerkit.uia.handlers.request.SafeRequestHandler;
import com.testerkit.uia.model.ResultObject;
import com.testerkit.uia.monitor.WatcherManager;
import com.testerkit.uia.requests.IRequest;
import com.testerkit.uia.requests.http.AppiumResponse;
import com.testerkit.uia.utils.dumps.XMLHierarchy;

import java.util.ArrayList;
import java.util.List;

public abstract class FindEvent extends SafeRequestHandler {

    public FindEvent(String mappedUri) {
        super(mappedUri);
    }

    protected String FUNC = "FindEvent";
    protected StepJson step;
    protected ResultObject result;

    @Override
    protected AppiumResponse safeHandle(IRequest request) throws Exception {
        Logger.info("Calling FindEvent... ");
        this.result = new ResultObject();
        //记录之前的状态用以还原，暂停监控系统框为长时暂停不能在步骤执行之后被false了。
        boolean status = WatcherManager.getInstance().isPausing();
        try {
            step = getStep(request);
            WatcherManager.getInstance().switchPause(true);
            if (step == null || executeFindEvent(request) == false) {
                return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, String.format(
                        "%s-->%s", this.result.getError(), step), ConstantResult.NO_FIND_ELEMENT);
            }
        } catch (Exception e) {
            Logger.error(e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e.getMessage(), ConstantResult.UIA_EXCEPTION);
        } finally {
            WatcherManager.getInstance().switchPause(status);
        }


        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, this.result.getValue());
    }

    protected List<NodeInfo> findNodes() {
        List<NodeInfo> nodes = new ArrayList<NodeInfo>();
        try {
            long start = System.currentTimeMillis();
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
                // TODO 如果没找到需要滑屏幕
            }

            if (nodes.size() == 0) {
                this.result.setError("没有找到一个元素！！！");
                Logger.iFunc(FUNC, this.result.getError());
            } else if (nodes.size() > 1) {
                this.result.setError("找到多个元素！！！" + GsonUtil.gsonString(nodes));
                Logger.iFunc(FUNC, this.result.getError());
            }
            return nodes;
        } catch (UIAException e) {
            this.result.setError("查找元素的时候异常！！！" + e.getMessage());
            Logger.error(this.result.getError(), e);
        } finally {

        }
        return nodes;
    }

    protected NodeInfo findNode() {
        List<NodeInfo> nodes = this.findNodes();
        if (nodes.size() != 1) {
            return null;
        }
        this.result.setValue(GsonUtil.gsonString(nodes.get(0)));
        return nodes.get(0);
    }

    protected abstract boolean executeFindEvent(IRequest request) throws Exception;
}
