package com.testerkit.uia.monitor.actions;

import com.testerkit.common.enums.ClickPosition;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.model.RectInfo;
import com.testerkit.common.watcher.actions.ActionAbstract;
import com.testerkit.uia.BaseContext;

import java.util.List;

/**
 * OPPO 新版本系统安装按钮特殊处理
 */
public class AOppoCompleteButtonClick extends ActionAbstract {
    public AOppoCompleteButtonClick(String key) {
        super(key);
    }

    @Override
    public boolean doAction(List<NodeInfo> nodes) {

        if(nodes == null || nodes.size() == 0){
            return false;
        }
        Logger.info("oppo mobile phone start simulation click !");
        NodeInfo node = nodes.get(0);
        // 如果找到特殊的大控件同时也找到了完成按钮，就直接点击完成
        if (node.getText().equalsIgnoreCase("完成")) {
            Logger.info( "simulation click : 完成");
            BaseContext.getInstance().getDevice().click(node.getRectVisible(), ClickPosition.CENTER);
            return true;
        }

        Logger.info( "OPPO手机新版本查找到安装控件！");
        // 模拟点击【安装】按钮位置
        boolean isOk =BaseContext.getInstance().getDevice().click(node.getRectVisible(), getClickPosition(node.getRectVisible()));
        if (isOk) {
            Logger.info(  "oppo mobile phone simulation click end!");
            return true;
        }
        return false;
    }

    /**
     * 获取父控件内的子控件排列结构
     *
     * @param rectInfo
     * @return
     */
    private ClickPosition getClickPosition(RectInfo rectInfo) {
        int rectHeight = rectInfo.height();
        // 如果父控件高度小于200像素，就认为是左右结构，返回点击右下角
        if (rectHeight < 200) {
            return ClickPosition.BOTTOM_RIGHT;
        } else {
            //  如果高度大于200，就认为是上下结构，返回点击控件中上部分
            return ClickPosition.TOP_CENTER;
        }
    }
}
