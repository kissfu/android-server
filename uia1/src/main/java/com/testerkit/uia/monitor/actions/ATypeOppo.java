package com.testerkit.uia.monitor.actions;

import android.view.accessibility.AccessibilityNodeInfo;

import com.testerkit.common.constants.ConstantWatcher;
import com.testerkit.common.enums.ClickPosition;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.utils.StringUtil;
import com.testerkit.common.watcher.actions.ActionAbstract;
import com.testerkit.common.watcher.criterias.Arr;
import com.testerkit.common.watcher.criterias.ArrCls;
import com.testerkit.common.watcher.criterias.ArrText;
import com.testerkit.common.watcher.search.ControlItem;
import com.testerkit.common.watcher.search.WatcherSearch;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.monitor.WatcherContext;
import com.testerkit.uia.monitor.WatcherManager;
import com.testerkit.uia.utils.dumps.AXWindowHelpers;

import java.util.List;


/**
 * @Author: able
 * @Description:输入密码方法
 * @Date: Created in 2019/6/1
 */
public class ATypeOppo extends ActionAbstract {

    public ATypeOppo(String key) {
        super(key);
    }

    @Override
    public boolean doAction(List<NodeInfo> nodes) {
        this.typeOppoPwd();

        /**
         只实用于oppo、vivo安装应用时输入密码后的点击。点击之后需要恢复没有键盘的状态。
         1、场景开始获取root的时候不能显示键盘，因为输入密码的场景rule，doMethod之后是隐藏键盘的。
         2、输入密码之后需要做点击，做点击是开始场景之前的root，所以必须也是隐藏键盘后的root。
         3、如果点击失败，需要还原没有键盘的root界面，不恢复的话，点击错误后会弹出键盘。
         */
        WatcherContext context = WatcherManager.getInstance().getContext();
        context.reSetRoots();
        AccessibilityNodeInfo[] roots = context.getRoots();
        String pkgName = "";
        if (roots == null || roots.length == 0 || roots[0] == null) {
            return false;
        }
        pkgName = AXWindowHelpers.getRootPackageName(roots[0]);
        if (StringUtil.isEmpty(pkgName)) {
            return false;
        }
        context.setPackageCurrent(pkgName);

        NodeInfo node = this.search(context); //ExtUiDevice.getTestinDevice().uiObjectWithTextMatch(root, texts, null, android.widget.Button.class, true);
        if (null == node) {
            return false;
        }
        boolean result = BaseContext.getInstance().getDevice().click(node.getRectVisible(), ClickPosition.CENTER);// Executor.doClickTextOnRect(myRectI);
        Logger.info("monitor--->ATypeOppo， click:" + node.getText());
        return result;
    }

    private NodeInfo search(WatcherContext context) {
        ControlItem item = new ControlItem();
        String[] texts = Arr.concatAll(ConstantWatcher.OK, ConstantWatcher.ALLOW, ConstantWatcher.NEXT);
        item.addToList(item.getTexts(), new ArrText(texts));
        item.addToList(item.getClasses(),new ArrCls(new String[]{android.widget.Button.class.getName()}));
        WatcherSearch search = new WatcherSearch(item);
        search.initAll();
        List<NodeInfo> nodes = search.isMatch(context.getDumpInfo());
        if(nodes.size() > 0){
            return  nodes.get(0);
        }
        return null;
    }

    /**
     * 处理oppo安装输入密码
     *
     * @return
     */
    private boolean typeOppoPwd() {
        Logger.info("monitor--->MTypeText typing password ！");
        return BaseContext.getInstance().getDevice().type(ConstantWatcher.OPPO_PWD);
    }
}
