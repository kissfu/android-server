package com.testerkit.common.steps.data.variableassign;

/**
 * @atuthor able
 */
public class DbInfo {
    /**
     * 数据库类型
     */
    private DbType type;
    private String sql;
    private String connectionString;
    private String userName;
    private String userPwd;
    private int rowIndex;
    private int columnIndex;
    private String columnName;
    /**
     * 数据库名称
     */
    private String name;
    private String ip;
    private String port;


    public DbType getType() {
        return type;
    }

    public void setType(DbType type) {
        this.type = type;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }


    @Override
    public String toString() {
        return "DbInfo{" +
                "type=" + type +
                ", sql='" + sql + '\'' +
                ", connectionString='" + connectionString + '\'' +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", rowIndex=" + rowIndex +
                ", columnIndex=" + columnIndex +
                ", columnName='" + columnName + '\'' +
                ", name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
