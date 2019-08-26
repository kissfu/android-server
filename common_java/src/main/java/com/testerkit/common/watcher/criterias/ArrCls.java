package com.testerkit.common.watcher.criterias;

import com.testerkit.common.watcher.search.GroupFlag;

public class ArrCls extends Arr {

    /**
     * @param arrPositive 匹配的数组
     */
    public ArrCls(String[] arrPositive) {
        super(arrPositive);
    }

    @Override
    public GroupFlag getGroupFlag() {
        return GroupFlag.CLASS;
    }
}
