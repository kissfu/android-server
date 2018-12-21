package com.testerkit.common.enums;

public enum XPathOption {
    ALL(0),
    NO_TEXT(1),
    NO_ID(2),
    NO_ID_TEXT(3),
    SIMPLE(4);

    private final int value;

    XPathOption(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}