package com.testerkit.common.model.criteria;

import com.testerkit.common.enums.Attribute;
import com.testerkit.common.model.NodeInfo;

import java.util.ArrayList;
import java.util.List;

public class UniqueGroup implements Comparable<UniqueGroup>{
    private int counter = 0;

    private List<CriteriaBase> list = new ArrayList<CriteriaBase>();

    public UniqueGroup(List<CriteriaBase> list) {
        this.list = list;
    }

    public void increase() {
        this.counter++;
    }

    public boolean isSame(NodeInfo nodeOther) {
        if (list.size() == 0) {
            return false;
        }
        boolean result = true;
        for (CriteriaBase c : list) {
            result &= c.isSame(nodeOther);
        }
        return result;
    }

    public boolean isUnique() {
        return counter == 1;
    }

    public String getUniMini(){
        StringBuilder sb = new StringBuilder();
        int indexLast = list.size() - 1;
        int index = 0;
        for (CriteriaBase c:list) {
            if(index == indexLast){
                sb.append(String.format("@%s='%s'",c.getAttribute().getName(),c.getText()));
            }else {
                sb.append(String.format("@%s='%s'",c.getAttribute().getName(),c.getText()));
                sb.append(" and ");
            }
            index ++;
        }
        if(sb.length() > 0){
            return  String.format("[%s]",sb);
        }
        return  sb.toString();
    }


    @Override
    public String toString() {
        return String.format("counter: %s, list: %s", counter, list.size());
    }

    @Override
    public int compareTo(UniqueGroup another) {
        if(this.list == null){
            //Logger.error("XPathInfo option is NULL!!!");
            return -1;
        }
        //自定义比较方法，如果认为此实体本身大则返回1，否则返回-1
        if (this.list.size() >= another.list.size()) {
            return 1;
        }
        return -1;
    }
}
