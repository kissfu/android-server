package com.testerkit.common.search.matcher;

import com.testerkit.common.enums.Attribute;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.model.UIDumpInfo;
import com.testerkit.common.search.by.ByBase;
import com.testerkit.common.search.matcher.MatcherBase;
import com.testerkit.common.utils.RegExUtil;

import java.util.ArrayList;
import java.util.List;

public class MatcherText extends MatcherBase<ByBase, UIDumpInfo> {

    public MatcherText(ByBase by, UIDumpInfo dump) {
        super(by, dump);
    }

    @Override
    public List<NodeInfo> findMatches() {
        List<NodeInfo> nodes = null;


        return nodes;
    }

    @Override
    public boolean isMatch(String value) {
        if (by.isMatchPre()) {
            return true;
        }
        if (value == null) {
            return false;
        }
        List<String> arr = by.getArr();
        //List<Boolean> results = new ArrayList<Boolean>();
        Boolean result = false;
        switch (by.getRelation()) {
            case OR:
                for (String criteria : arr) {
                    boolean isOk = isMeetItem(criteria, value);
                    if (isOk == true) {
                        return true;
                    }
                }
                result = false;
                break;
            case AND:
                for (String criteria : arr) {
                    boolean isOk = isMeetItem(criteria, value);
                    if (isOk == false) {
                        return false;
                    }
                }
                result = true;
                break;
        }
        return result;
    }

    @Override
    public boolean isMatch(NodeInfo node) {
        return isMatch(getValue(node));
    }

    //TODO ByBase条件 和 属性对应关系
    private String getValue(NodeInfo node){
        Attribute attribute = by.getAttribute();
        if(attribute == Attribute.CLASS){
            return node.getClazzName();
        }else if(attribute == Attribute.NAME){
            return node.getName();
        }else  if(attribute == Attribute.PACKAGE){
            return node.getPackageName();
        }else if(attribute == Attribute.TEXT){
            return node.getText();
        }else if(attribute == Attribute.CONTENT_DESC){
            return node.getContentDesc();
        }
        return null;
    }


    @Override
    public NodeInfo findMatch() {
        return null;
    }


    private boolean isMeetItem(String criteria, String value) {
        if (criteria.startsWith(RegExUtil.REGULAR) && criteria.endsWith(RegExUtil.REGULAR)) {
            criteria = criteria.substring(1, criteria.lastIndexOf(RegExUtil.REGULAR));
            return RegExUtil.isMatch(criteria, value);

        }
        criteria = by.compatibleMode(criteria);
        return RegExUtil.isMatchWithStar(criteria, value);
    }
}
