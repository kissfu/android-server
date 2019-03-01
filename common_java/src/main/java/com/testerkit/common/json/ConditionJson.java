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
    private ByPackageName packageName;
    private ByXPath xpath;

    //transient永久关闭序列化
//    private transient List<ByBase> bys;

    public ConditionJson() {
//        bys = new ArrayList<ByBase>();
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
//        this.bys.add(name);
    }

    public ByClazz getClazz() {
        return clazz;
    }

    public void setClazz(ByClazz clazz) {
        this.clazz = clazz;
//        this.bys.add(clazz);
    }

    public ByText getText() {
        return text;
    }

    public void setText(ByText text) {
        this.text = text;
//        this.bys.add(text);
    }

    public ByPackageName getPackageName() {
        return packageName;
    }

    public void setPackageName(ByPackageName packageName) {
        this.packageName = packageName;
//        this.bys.add(packageName);
    }

    public ByXPath getXpath() {
        return xpath;
    }

    public void setXpath(ByXPath xpath) {
        this.xpath = xpath;
//        this.bys.add(xpath);
    }

//    public List<ByBase> getBys() {
//        return bys;
//    }
}
