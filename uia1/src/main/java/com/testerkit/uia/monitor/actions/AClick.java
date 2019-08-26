package com.testerkit.uia.monitor.actions;

import com.testerkit.common.enums.ClickPosition;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.utils.StringUtil;
import com.testerkit.common.watcher.actions.ActionAbstract;
import com.testerkit.common.watcher.rules.ClickPriority;
import com.testerkit.uia.BaseContext;

import java.util.List;

public class AClick  extends ActionAbstract {

    private ClickPosition clickPosition = ClickPosition.CENTER;
    //点击按钮优先级，可以优先Button控件点击，然后其他控件点击
    private ClickPriority clickPriority = ClickPriority.NONE;

    public AClick(String key) {
        super(key);
    }
    public AClick(String key,ClickPriority clickPriority) {
        super(key);
        this.clickPriority = clickPriority;
    }
    public AClick(String key,ClickPosition clickPosition) {
        this(key);
        this.clickPosition = clickPosition;
    }

    @Override
    public boolean doAction(List<NodeInfo> nodes) {
        if(nodes == null || nodes.size() == 0){
            return false;
        }

        switch (clickPriority){
            case BUTTON:

                for (NodeInfo n : nodes) {
                    String cls = n.getClazzName();
                    if(StringUtil.isEmpty(cls)){
                        continue;
                    }
                    if(cls.toLowerCase().contains("button")){
                        return  BaseContext.getInstance().getDevice().click(n.getRectVisible(),clickPosition);
                    }
                }
                break;
        }
        Logger.info("watcher click myRect:" + nodes.get(0).getText());
        boolean result = BaseContext.getInstance().getDevice().click(nodes.get(0).getRectVisible(),clickPosition);
        return result;
    }
}
