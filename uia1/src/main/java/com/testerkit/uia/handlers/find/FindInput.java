package com.testerkit.uia.handlers.find;

import com.testerkit.common.exceptions.UIAException;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.model.RectInfo;
import com.testerkit.common.utils.GsonUtil;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.requests.IRequest;

public class FindInput extends FindEvent {

    public FindInput(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected boolean executeFindEvent(IRequest request) throws Exception {

        try {

            NodeInfo node = this.findNode();
            if(node == null){
                return false;
            }
            RectInfo rect = node.getRectVisible();
            boolean isOk = BaseContext.getInstance().getDevice().click(rect.centerX(), rect.centerY());
            if (isOk == false) {
                this.result.setError("找到元素但是点击失败！！！");
                return false;
            }
            isOk = BaseContext.getInstance().getDevice().type(step.getNode().getText());
            if (isOk == false) {
                this.result.setError("输入文本失败！！！");
                return false;
            }
            this.result.setValue(GsonUtil.gsonString(node));
            return isOk;

        } catch (UIAException e) {
            this.result.setError("查找元素的时候异常！！！" + e.getMessage());
            Logger.error(this.result.getError(), e);
        } finally {

        }
        return false;
    }
}
