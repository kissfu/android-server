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

public class FindSelection extends FindEvent {

    public FindSelection(String mappedUri) {
        super(mappedUri);
    }

    private int len = 6;

    @Override
    protected boolean executeFindEvent(IRequest request) throws Exception {
        try {
            NodeInfo node = this.findNode();
            if (node == null) {
                return false;
            }
            SdFindSelection data = GsonUtil.toBean(this.getStepRaw(request), SdFindSelection.class);
            this.result.setValue(GsonUtil.gsonString(node));
            if (data.getEleType() == 1) {
                return swipe2(node.getRectVisible(), data.getVariable().getValue(), data.getArrIndex());
            }
            return swipe(node.getRectVisible(), data.getVariable().getValue(), data.getArrIndex());

        } catch (UIAException e) {
            this.result.setError("查找元素的时候异常！！！" + e.getMessage());
            Logger.error(this.result.getError(), e);
        } finally {

        }
        return false;
    }

    //大框中间的元素
    public boolean swipe2(RectInfo rect, String selected, int index) {
        boolean isok = false;
        // 没有内容
        if (StringUtil.isEmpty(selected)) {
            return true;
        }

        if (index == -1) {
            return false;
        }
        int times = index + 1;
        int hBig = rect.height() * 1;
        SwipeInfo swipe = new SwipeInfo();
        swipe.setStartX(rect.width() / 2);
        swipe.setStartY(rect.top - hBig);

        swipe.setEndX(rect.width() / 2);
        swipe.setEndY(rect.bottom + hBig);

        try {
            // 从上往下滑
            isok = BaseContext.getInstance().getDevice().swipe(swipe.getStartX(), swipe.getStartY(), swipe.getEndX(), swipe.getEndY(), 10);
            SleepUtil.sleep(2);
        } catch (Exception e) {
            Logger.error(e);
        }

        SizeInfo size = new SizeInfo();
        size.height = rect.height();
        try {
            swipe.setStartY(rect.top);
            for (int i = 0; i < times; i++) {
                // 一个一个上滑
                isok = BaseContext.getInstance().getDevice().swipe(swipe.getStartX(), swipe.getStartY(), size, ScrollDirection.UP, 3);
            }
        } catch (Exception e) {
            Logger.error(e);
        }
        return isok;
    }

    // 大框
    public boolean swipe(RectInfo rect, String selected, int index) {
        boolean isok = false;
        // 没有内容
        if (StringUtil.isEmpty(selected)) {
            return true;
        }

        if (index == -1) {
            return false;
        }
        int times = index + 1;
        int h1 = rect.height() / len;
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
