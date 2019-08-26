package com.testerkit.common.watcher.criterias;

import com.testerkit.common.watcher.search.GroupFlag;

public class ArrId extends Arr {

    /**
     * @param arrPositive 匹配的数组
     */
    public ArrId(String[] arrPositive) {
        super(arrPositive);
    }

    @Override
    public GroupFlag getGroupFlag() {
        return GroupFlag.ID;
    }
}
