package com.testerkit.uia.monitor.actions;

import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.watcher.actions.ActionAbstract;
import com.testerkit.uia.monitor.WatcherManager;

import java.util.List;

public class AStopApp extends ActionAbstract {
    public AStopApp(String key) {
        super(key);
    }

    @Override
    public boolean doAction(List<NodeInfo> nodes) {

        //忽略策略返回的结果集合
        String packageCurrent = WatcherManager.getInstance().getContext().getPackageCurrent();
        Logger.info( "force-stop packageName:" + packageCurrent);
        //终止进程包名
        try {
            String cmd = "am force-stop " + packageCurrent;
            Runtime.getRuntime().exec(cmd).waitFor();
            return true;
        } catch (Exception e) {
            Logger.error( e.getMessage(), e);
        }
        return false;
    }

}
