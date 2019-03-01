package com.testerkit.common.search.by;

import com.testerkit.common.enums.Attribute;
import com.testerkit.common.enums.ByOption;
import com.testerkit.common.enums.Relation;
import com.testerkit.common.utils.StringUtil;

import java.util.List;

public abstract class ByBase {

    protected ByOption option = ByOption.REQUIRED;
    //关系 只使用于需要匹配的数组
    protected Relation relation = Relation.AND;


    protected final List<String> arr;

    public List<String> getArr() {
        return arr;
    }

    public ByOption getOption() {
        return option;
    }

    public void setOption(ByOption option) {
        this.option = option;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public ByBase(List<String> arr) {
        this.arr = arr;
    }

    public abstract List<String> compatibleMode();
    public abstract String compatibleMode(String item);
    public abstract Attribute getAttribute();

    public boolean isMatchPre() {
        if (this.option == null || this.option == ByOption.IGNORED) {
            return true;
        }
        if(arr == null || arr.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * 根据isMatchPre 过滤 ，获取真实的比对条件
     * @return
     */
    public String getElementLocator() {
        if(isMatchPre())return "";
        return StringUtil.join(arr.toArray(), ",");
    }

    //region Override

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ByBase by = (ByBase) o;

        return toString().equals(by.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        // A stub to prevent endless recursion in hashCode()
        return "By.Base: " + getElementLocator();
    }

    //endregion
}
