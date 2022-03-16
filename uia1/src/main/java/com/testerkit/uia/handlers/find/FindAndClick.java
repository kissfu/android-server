package com.testerkit.uia.handlers.find;

import com.testerkit.common.exceptions.UIAException;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.model.RectInfo;
import com.testerkit.common.steps.data.touchclick.ClickType;
import com.testerkit.common.utils.GsonUtil;
import com.testerkit.common.utils.SleepUtil;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.requests.IRequest;

public class FindAndClick extends FindEvent {

    public FindAndClick(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected boolean executeFindEvent(IRequest request) throws Exception {

        try {

            NodeInfo node = this.findNode();
            if(node == null){
                return false;
            }
            RectInfo rect =node.getRectVisible();
            boolean isOk = false;
            if(step.getClickType() == null){
                step.setClickType(ClickType.CLICK);
            }
            switch (step.getClickType()){
                case DBCLICK:
                    isOk = BaseContext.getInstance().getDevice().click(rect.centerX(), rect.centerY());
                    SleepUtil.sleep(200L);
                    isOk = BaseContext.getInstance().getDevice().click(rect.centerX(), rect.centerY());
                    break;
                case LONG_CLICK:
                    long duration = 2000;
                    isOk = BaseContext.getInstance().getDevice().click(rect.centerX(), rect.centerY(),duration);
                    break;
                case CLICK:
                    isOk = BaseContext.getInstance().getDevice().click(rect.centerX(), rect.centerY());
                    break;
                default:
                    isOk = BaseContext.getInstance().getDevice().click(rect.centerX(), rect.centerY());
                    break;
            }
            if (isOk == false) {
                this.result.setError("找到元素但是点击失败！！！");
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
