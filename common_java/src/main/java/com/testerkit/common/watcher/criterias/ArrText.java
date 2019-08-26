package com.testerkit.common.watcher.criterias;


import com.testerkit.common.enums.Relation;
import com.testerkit.common.watcher.search.GroupFlag;

public class ArrText extends Arr {

    /**
     * @param arrPositive 匹配的数组
     */
    public ArrText(String[] arrPositive) {
        super(arrPositive);
    }

    /**
     * @param arrPositive 匹配的数组
     * @param arrNegation 排除的数组
     */
    public ArrText(String[] arrPositive, String[] arrNegation) {
        super(arrPositive, arrNegation);
    }

    /**
     *
     * @param relation 匹配数组的关系
     * @param arrPositive 匹配的数组
     */
    public ArrText(Relation relation, String[] arrPositive) {
        super(relation, arrPositive);
    }


    @Override
    public GroupFlag getGroupFlag() {
        return GroupFlag.TEXT;
    }
}
