package com.testerkit.uia.model;

public class ResultObject {
    private String error = "";
    private Object value = "true";

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
