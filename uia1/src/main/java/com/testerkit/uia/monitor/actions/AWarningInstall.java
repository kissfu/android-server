package com.testerkit.uia.monitor.actions;

import android.view.accessibility.AccessibilityNodeInfo;

import com.testerkit.common.enums.ClickPosition;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.utils.SleepUtil;
import com.testerkit.common.utils.StringUtil;
import com.testerkit.common.watcher.actions.ActionAbstract;
import com.testerkit.common.watcher.criterias.ArrCls;
import com.testerkit.common.watcher.criterias.ArrText;
import com.testerkit.common.watcher.search.ControlItem;
import com.testerkit.common.watcher.search.WatcherSearch;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.monitor.WatcherContext;
import com.testerkit.uia.monitor.WatcherManager;
import com.testerkit.uia.utils.dumps.AXWindowHelpers;

import java.util.List;

public class AWarningInstall extends ActionAbstract {
    public AWarningInstall(String key) {
        super(key);
    }

    @Override
    public boolean doAction(List<NodeInfo> nodes) {
        if (nodes == null || nodes.size() == 0) {
            return false;
        }

        NodeInfo node = nodes.get(0);
        BaseContext.getInstance().getDevice().click(node.getRectVisible(), ClickPosition.CENTER);
        Logger.info("warning install diaglog first click 1:" + node.getRectVisible());

        // 等待500ms后，然后获取Root，继续查找安装按钮，因为有些手机（vivo）在点击掉风险提示的对话框的【继续安装】之后，等待的时间过长会导致下一个安装页面自动消失
        SleepUtil.sleep(500L);


        WatcherContext context = WatcherManager.getInstance().getContext();
        context.reSetRoots();
        AccessibilityNodeInfo[] root = context.getRoots();
        String pkgName = "";
        if (root == null || root.length == 0 || root[0] == null) {
            return false;
        }
        pkgName = AXWindowHelpers.getRootPackageName(root[0]);
        if (StringUtil.isEmpty(pkgName)) {
            return false;
        }
        context.setPackageCurrent(pkgName);
        if (context.isSysPackage() == false) {
            return false;
        }

        NodeInfo node2 = this.search(context);
        if (null == node2) {
            return false;
        }
        boolean result = BaseContext.getInstance().getDevice().click(node2.getRectVisible(), ClickPosition.CENTER);
        Logger.info("monitor--->AWarningInstall， click 2:" + node2.getText());
        return result;
    }

    private NodeInfo search(WatcherContext context) {
        ControlItem item = new ControlItem();
        String[] texts = new String[]{"继续安装", "安装", "安裝"};
        item.addToList(item.getTexts(),new ArrText(texts));
        item.addToList(item.getClasses(),new ArrCls(new String[]{android.widget.Button.class.getName()}));
        WatcherSearch search = new WatcherSearch(item);
        search.initAll();
        List<NodeInfo> nodes = search.isMatch(context.getDumpInfo());
        if (nodes.size() > 0) {
            return nodes.get(0);
        }

        return null;
    }

}
