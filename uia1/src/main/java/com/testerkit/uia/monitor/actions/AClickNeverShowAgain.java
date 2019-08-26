package com.testerkit.uia.monitor.actions;

import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.utils.SleepUtil;
import com.testerkit.common.watcher.actions.ActionAbstract;
import com.testerkit.common.watcher.criterias.ArrText;
import com.testerkit.common.watcher.search.ControlItem;
import com.testerkit.common.watcher.search.WatcherSearch;
import com.testerkit.uia.monitor.WatcherContext;
import com.testerkit.uia.monitor.WatcherManager;
import com.testerkit.uia.utils.ClickUtil;

import java.util.List;

public class AClickNeverShowAgain extends ActionAbstract {
    private boolean checked = true;

    /**
     * checked 默认是true
     *
     * @param key
     */
    public AClickNeverShowAgain(String key) {
        super(key);
    }

    /**
     * @param key
     * @param checked 默认是true
     */
    public AClickNeverShowAgain(String key, boolean checked) {
        super(key);
        this.checked = checked;
    }

    @Override
    public boolean doAction(List<NodeInfo> nodes) {
        if (nodes == null || nodes.size() == 0) {
            return false;
        }
        try {
            NodeInfo node = nodes.get(0);
            if (node.isChecked() == this.checked) {
                return true;
            }
            // 当有 “不再提示” 的时候先判断它是否 和checked状态一样，如果不是先点击一下

            if (node.isCheckable()) {
                Logger.info("first ClickNeverShowAgain 1,checked must : " + this.checked);
                ClickUtil.clickCenter(node.getRectVisible());
                SleepUtil.sleep(500L);
            }

            // validation
            NodeInfo node2 = search(new String[]{node.getText()});
            if (node2 != null && node2.isChecked() != this.checked) {
                Logger.info("second ClickNeverShowAgain 2,checked must : " + this.checked);
                ClickUtil.clickOnLeft(node.getRectVisible());
            }

        } catch (Exception e) {
            Logger.error("monitor--->AClickNeverShowAgain,error:" + e.getMessage(), e);
            return false;
        }
        return true;
    }

    private NodeInfo search(String[] texts) {
        WatcherContext context = WatcherManager.getInstance().getContext();
        context.reSetRoots();
        ControlItem item = new ControlItem();
        item.addToList(item.getTexts(), new ArrText(texts));

        WatcherSearch search = new WatcherSearch(item);
        search.initAll();
        List<NodeInfo> nodes = search.isMatch(context.getDumpInfo());
        if (nodes.size() > 0) {
            return nodes.get(0);
        }
        return null;
    }


}
