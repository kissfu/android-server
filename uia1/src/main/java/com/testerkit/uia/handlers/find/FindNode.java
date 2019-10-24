package com.testerkit.uia.handlers.find;

import com.testerkit.common.exceptions.UIAException;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.utils.GsonUtil;
import com.testerkit.uia.requests.IRequest;

import java.util.List;

public class FindNode extends FindEvent {

    public FindNode(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected boolean executeFindEvent(IRequest request) throws Exception {
        try {
            List<NodeInfo> nodes = this.findNodes(false);
            if (nodes.size() == 0) {
                return false;
            }
            this.result.setValue(GsonUtil.gsonString(nodes.get(0)));
            return true;

        } catch (UIAException e) {
            this.result.setError("查找元素的时候异常！！！" + e.getMessage());
            Logger.error(this.result.getError(), e);
        } finally {

        }
        return false;
    }
}
