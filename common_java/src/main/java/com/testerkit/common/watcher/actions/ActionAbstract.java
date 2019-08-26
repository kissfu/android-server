package com.testerkit.common.watcher.actions;

import com.testerkit.common.model.NodeInfo;

import java.util.List;

public abstract class ActionAbstract {

    protected String key = "";

    public String getKey() {
        return key;
    }

    public ActionAbstract(String key) {
        this.key = key;
    }

    public abstract boolean doAction(List<NodeInfo> nodes);
}
