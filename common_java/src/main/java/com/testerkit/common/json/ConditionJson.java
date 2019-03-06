package com.testerkit.common.json;

import com.testerkit.common.enums.Relation;
import com.testerkit.common.search.by.ByBase;
import com.testerkit.common.search.by.ByClazz;
import com.testerkit.common.search.by.ByName;
import com.testerkit.common.search.by.ByPackageName;
import com.testerkit.common.search.by.ByText;
import com.testerkit.common.search.by.ByXPath;

import java.util.ArrayList;
import java.util.List;

public class ConditionJson {
    private Relation relation = Relation.AND;
    private ByName name;
    private ByClazz clazz;
    private ByText text;
    private ByText contentDesc;
    private ByPackageName packageName;
    private ByXPath xpath;

    public ConditionJson() {
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public ByName getName() {
        return name;
    }

    public void setName(ByName name) {
        this.name = name;
    }

    public ByClazz getClazz() {
        return clazz;
    }

    public void setClazz(ByClazz clazz) {
        this.clazz = clazz;
    }

    public ByText getText() {
        return text;
    }

    public void setText(ByText text) {
        this.text = text;
    }

    public ByText getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(ByText contentDesc) {
        this.contentDesc = contentDesc;
    }

    public ByPackageName getPackageName() {
        return packageName;
    }

    public void setPackageName(ByPackageName packageName) {
        this.packageName = packageName;
    }

    public ByXPath getXpath() {
        return xpath;
    }

    public void setXpath(ByXPath xpath) {
        this.xpath = xpath;
    }

}
