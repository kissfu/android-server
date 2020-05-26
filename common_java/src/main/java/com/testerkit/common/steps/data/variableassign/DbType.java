package com.testerkit.common.steps.data.variableassign;

/**
 * @atuthor able
 */
public enum DbType {
    MYSQL(1),
    ORACLE(2),
    SQLSERVER(3),
    DB2(4);
    private int type;

    private DbType(int type) {
        this.type = type;

    }

    public int getType() {
        return type;
    }
}
