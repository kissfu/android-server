package com.testerkit.uia.handlers.find;

import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.model.RectInfo;
import com.testerkit.common.model.UIDumpInfo;
import com.testerkit.common.search.matcher.MatcherManager;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.exceptions.UIAException;
import com.testerkit.uia.requests.IRequest;
import com.testerkit.uia.requests.http.AppiumResponse;
import com.testerkit.common.enums.WDStatus;
import com.testerkit.uia.utils.ReflectionUtils;
import com.testerkit.uia.utils.dumps.XMLHierarchy;

public class FindAndClick extends FindEvent {

    public FindAndClick(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected boolean executeFindEvent(IRequest request) throws Exception {


        try {
            ReflectionUtils.clearAccessibilityCache();
            UIDumpInfo dump = XMLHierarchy.getDumpInfo();
            NodeInfo node = MatcherManager.getInstance().isMatchSingle(step, dump);
            if (node == null) {
                Logger.iFunc(FUNC," can not find node!!!");
                return false;
            }
            RectInfo rect = node.getRectVisible();
            return BaseContext.getInstance().getDevice().click(rect.centerX(), rect.centerY());

        } catch (UIAException e) {
            Logger.error("Exception while performing dump SourceNode action: ", e);
        }
        return false;
    }
}
