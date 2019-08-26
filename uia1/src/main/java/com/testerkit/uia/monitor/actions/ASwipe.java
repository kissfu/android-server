package com.testerkit.uia.monitor.actions;

import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.watcher.actions.ActionAbstract;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.model.ScreenSize;

import java.util.List;

/**
 * 滑屏方法
 */
public class ASwipe extends ActionAbstract {


    public ASwipe(String key) {
        super(key);
    }

    @Override
    public boolean doAction(List<NodeInfo> nodes) {
        Logger.info( "VIVO phone sliding screen to unlock !");
        swipeScreenShot();
        return true;
    }

    private void swipeScreenShot() {
        ScreenSize size = BaseContext.getInstance().getDevice().getScreenSize();
        int height = size.getHeight();
        int width = size.getWidth();
        Logger.info("Height: " + height + " Width: " + width);
        BaseContext.getInstance().getDevice().swipe(width / 2, height - 20, width / 2, height / 2, 10);
    }
}
