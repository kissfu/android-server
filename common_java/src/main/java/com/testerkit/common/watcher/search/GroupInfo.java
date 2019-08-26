package com.testerkit.common.watcher.search;

import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.model.UIDumpInfo;
import com.testerkit.common.utils.RegExUtil;
import com.testerkit.common.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class GroupInfo {

    private List<ControlItem> items = new ArrayList<ControlItem>();
    private ControlItem all = new ControlItem();
    private String type = "";


    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public List<ControlItem> getItems() {
        return items;
    }


    public void initAll() {
        for (ControlItem item : items) {
            this.filter(this.all.getClasses(), item.getClasses());
            this.filter(this.all.getTexts(), item.getTexts());
            this.filter(this.all.getIds(), item.getIds());
        }
        this.all.setRegexClasses();
        this.all.setRegexIds();
        this.all.setRegexTexts();
    }

    private void filter(List<String> destList, List<String> srcList) {
        for (String str : srcList) {
            if (destList.contains(str)) {
                continue;
            }
            destList.add(str);
        }
    }


    public List<NodeInfo> isMatch(UIDumpInfo dump) {
        List<NodeInfo> nodes = new ArrayList<NodeInfo>();
        if(StringUtil.isEmpty(this.type)){
            Logger.iFunc(this.type, "group tye is null");
            return nodes;
        }
        if (Logger.IS_OPEN) {
            Logger.iFunc(this.type, "isMatch  class regex:" + this.all.getRegexClasses());
            Logger.iFunc(this.type , "isMatch id(name) regex:" + this.all.getRegexIds());
            Logger.iFunc(this.type, "isMatch text regex:" + this.all.getRegexTexts());
        }
        for (NodeInfo node : dump.getNodes()) {
            // || isMatch(this.all.getRegexTexts(), node.getContentDesc()) 忽略contentDesc
            boolean isOk = isMatch(this.all.getRegexClasses(), node.getClazzName())
                    && isMatch(this.all.getRegexIds(), node.getName())
                    && (isMatch(this.all.getRegexTexts(), node.getText()));
            if (isOk) {
                if (Logger.IS_OPEN) {
                    Logger.iFunc(this.type ,"isMatch node:" +node.toStringShort());
                }
                nodes.add(node);
            }
        }
        return nodes;
    }

    private boolean isMatch(String criteria, String value) {

        // 如果是空的条件表示忽略
        if (StringUtil.isEmpty(criteria)) {
            return true;
        }
        if (StringUtil.isEmpty(value)) {
            return false;
        }

        return RegExUtil.isMatch(criteria, value);

    }

    @Override
    public String toString() {
        return "GroupInfo{" +
                "items=" + items.size() +
//                ", all=" + all +
                ", [type]='" + type + '\'' +
                '}';
    }
}
