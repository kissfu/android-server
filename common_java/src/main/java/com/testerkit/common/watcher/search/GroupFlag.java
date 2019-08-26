package com.testerkit.common.watcher.search;

public enum GroupFlag {
    CLASS("C"),
    ID("I"),
    TEXT("T"),
    PACKAGE("P"),
    BRAND("B");

    private final String message;

    GroupFlag(final String value) {
        this.message = value;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
