package com.testerkit.uia.monitor.actions;

import com.testerkit.common.enums.KeyEnum;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.watcher.actions.ActionAbstract;
import com.testerkit.uia.BaseContext;

import java.util.List;

public class APressBack extends ActionAbstract {
    public APressBack(String key) {
        super(key);
    }

    @Override
    public boolean doAction(List<NodeInfo> nodes) {
        return BaseContext.getInstance().getDevice().pressKey(KeyEnum.BACK.toString());
    }


}
