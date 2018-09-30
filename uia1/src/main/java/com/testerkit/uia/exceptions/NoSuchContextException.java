package com.testerkit.uia.exceptions;

public class NoSuchContextException extends UIAException {

    public NoSuchContextException(String detailMessage) {
        super(detailMessage);
    }

    public NoSuchContextException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public NoSuchContextException(Throwable throwable) {
        super(throwable);
    }
}
