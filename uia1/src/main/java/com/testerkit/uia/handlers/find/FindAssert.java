package com.testerkit.uia.handlers.find;

import com.testerkit.common.exceptions.UIAException;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.uia.requests.IRequest;

import java.util.List;

public class FindAssert extends FindEvent {

    public FindAssert(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected boolean executeFindEvent(IRequest request) throws Exception {

        try {
            List<NodeInfo> nodes = this.findNodes();
            if (nodes.size() == 0) {
                return false;
            }
            return true;

        } catch (UIAException e) {
            this.result.setError("查找元素的时候异常！！！" + e.getMessage());
            Logger.error(this.result.getError(), e);
        } finally {

        }
        return false;
    }
}
