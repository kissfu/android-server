package com.testerkit.common.enums;

public enum Relation {
    OR("或"),
    AND("和");
    private String text;

    private Relation(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
