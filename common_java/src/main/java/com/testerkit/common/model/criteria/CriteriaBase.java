package com.testerkit.common.model.criteria;

import com.testerkit.common.enums.Attribute;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.model.UIDumpInfo;
import com.testerkit.common.search.by.ByBase;
import com.testerkit.common.utils.RegExUtil;
import com.testerkit.common.utils.StringUtil;

import java.util.List;

public class CriteriaBase implements ICriteria<NodeInfo> {



    private Attribute attribute;
    private String text = "";

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CriteriaBase(String text) {
        this.text = text;
    }

    public CriteriaBase(Attribute attribute,String text) {
        this.attribute = attribute;
        this.text = text;
    }
    //TODO 文本条件 和 属性对应关系
    private String getValue(NodeInfo nodeOther){
        if(attribute == Attribute.TEXT){
            return nodeOther.getText();
        }else if(attribute == Attribute.NAME){
            return nodeOther.getName();
        }if(attribute == Attribute.CLASS){
            return nodeOther.getClazzName();
        }if(attribute == Attribute.CONTENT_DESC){
            return nodeOther.getContentDesc();
        }
        return null;
    }

    public boolean isSame(NodeInfo node){
        if(StringUtil.isNullOrEmpty(text)){
            return true;
        }
        return text.equals(getValue(node));
    }

    @Override
    public String toString() {
        return String.format("key: %s, value: %s",attribute,text);
    }
}
