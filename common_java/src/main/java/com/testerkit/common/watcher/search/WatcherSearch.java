package com.testerkit.common.watcher.search;

import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.model.UIDumpInfo;

import java.util.ArrayList;
import java.util.List;

public class WatcherSearch {

    private final List<ControlItem> items;
    private List<GroupInfo> groups;


    public WatcherSearch(List<ControlItem> items) {
        this.items = items;
    }

    public WatcherSearch(ControlItem item) {
        this.items = new ArrayList<ControlItem>();
        this.items.add(item);
    }

    public void initAll() {
        this.groups = new ArrayList<GroupInfo>();
        for (ControlItem item : items) {
            GroupInfo g = this.getGroup(item);
            if (g == null) {
                g = new GroupInfo();
                g.setType(item.getGroupType());
                this.groups.add(g);
            }
            g.getItems().add(item);
        }
        for (GroupInfo g : this.groups) {
            Logger.info("group init all :" + g);
            g.initAll();
        }

    }

    private GroupInfo getGroup(ControlItem item) {
        for (GroupInfo g : this.groups) {
            if (g.getType().equals(item.getGroupType())) {
                return g;
            }
        }
        return null;
    }


    public List<NodeInfo> isMatch(UIDumpInfo dump) {
        List<NodeInfo> nodes = new ArrayList<NodeInfo>();
        for (GroupInfo g : this.groups) {
            List<NodeInfo> tempNodes = g.isMatch(dump);
            for (NodeInfo n : tempNodes) {
                if (nodes.contains(n)) {
                    Logger.info("[XXX] duplicate match node:"+n.toStringShort());
                    continue;
                }
                nodes.add(n);
            }
        }
        return nodes;
    }
}
