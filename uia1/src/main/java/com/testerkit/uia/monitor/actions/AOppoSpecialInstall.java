package com.testerkit.uia.monitor.actions;

import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.model.RectInfo;
import com.testerkit.common.watcher.actions.ActionAbstract;
import com.testerkit.uia.BaseContext;

import java.util.List;


/**
 * 在当前包名下，不能找到【继续安装】但能找到【软件商店安装】
 */
public class AOppoSpecialInstall extends ActionAbstract {
    public AOppoSpecialInstall(String key) {
        super(key);
    }

    @Override
    public boolean doAction(List<NodeInfo> nodes) {
        try {
            if(nodes == null || nodes.size() == 0){
                return false;
            }
            RectInfo rect = nodes.get(0).getRectVisible();
            int height = BaseContext.getInstance().getDevice().getScreenSize().getHeight();
            int x = rect.centerX();
            int yCenter = rect.centerY();
            int y = yCenter + ((height - yCenter) / 2);
            boolean result = BaseContext.getInstance().getDevice().click(x, y);
            Logger.info(String.format("special install---click x:%s,y:%s,result:%s", x, y, result));
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }

        return true;
    }
}
