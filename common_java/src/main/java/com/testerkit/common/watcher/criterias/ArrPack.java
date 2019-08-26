package com.testerkit.common.watcher.criterias;


import com.testerkit.common.watcher.search.GroupFlag;

public class ArrPack extends Arr {

    /**
     * @param arrPositive 匹配的数组
     */
    public ArrPack(String[] arrPositive) {
        super(arrPositive);
    }

    /**
     * @param arrPositive 匹配的数组
     * @param arrNegation 排除的数组
     */
    public ArrPack(String[] arrPositive, String[] arrNegation) {
        super(arrPositive, arrNegation);
    }

    @Override
    public GroupFlag getGroupFlag() {
        return GroupFlag.PACKAGE;
    }
}
