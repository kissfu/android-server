package com.testerkit.uia.handlers.find;

import com.testerkit.common.enums.ScrollDirection;
import com.testerkit.common.exceptions.UIAException;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.model.RectInfo;
import com.testerkit.common.model.SizeInfo;
import com.testerkit.common.model.SwipeInfo;
import com.testerkit.common.steps.data.SdFindSelection;
import com.testerkit.common.utils.GsonUtil;
import com.testerkit.common.utils.SleepUtil;
import com.testerkit.common.utils.StringUtil;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.requests.IRequest;

import java.util.Arrays;
import java.util.List;

public class FindSelection extends FindEvent {

    public FindSelection(String mappedUri) {
        super(mappedUri);
    }

    private List<String> maturityArr = Arrays.asList("三月", "六月", "一年", "二年", "三年", "五年");

    @Override
    protected boolean executeFindEvent(IRequest request) throws Exception {
        try {
            NodeInfo node = this.findNode();
            if (node == null) {
                return false;
            }
            SdFindSelection data = GsonUtil.toBean(this.getStepRaw(request), SdFindSelection.class);
            this.result.setValue(GsonUtil.gsonString(node));
            return swipe(node.getRectVisible(), data.getVariable().getValue());

        } catch (UIAException e) {
            this.result.setError("查找元素的时候异常！！！" + e.getMessage());
            Logger.error(this.result.getError(), e);
        } finally {

        }
        return false;
    }

    public boolean swipe(RectInfo rect, String selected) {
        boolean isok = false;
        // 没有内容
        if(StringUtil.isEmpty(selected)){
            return true;
        }
        int times = maturityArr.indexOf(selected.trim());
        if(times == -1){
            return false;
        }

        int h1 = rect.height() / maturityArr.size();
        SwipeInfo swipe = new SwipeInfo();
        swipe.setStartX(rect.width() / 2);
        swipe.setStartY(rect.top + h1);

        swipe.setEndX(rect.width() / 2);
        swipe.setEndY(rect.bottom - h1);

        try {
            // 从上往下滑
            isok = BaseContext.getInstance().getDevice().swipe(swipe.getStartX(), swipe.getStartY(), swipe.getEndX(), swipe.getEndY(), 10);
            SleepUtil.sleep(2);
        } catch (Exception e) {
            Logger.error(e);
        }

        SizeInfo size = new SizeInfo();
        size.height = h1;
        try {
            swipe.setStartY(rect.top + h1 * 4);
            for (int i = 0; i < times; i++) {
                // 一个一个上滑
                isok = BaseContext.getInstance().getDevice().swipe(swipe.getStartX(), swipe.getStartY(), size, ScrollDirection.UP, 3);
            }
        } catch (Exception e) {
            Logger.error(e);
        }
        return isok;
    }
}
