package com.testerkit.common.exceptions;

public class UIANotConnected extends  UIAException {

    private static final long serialVersionUID = -1592305571101012889L;

    public UIANotConnected(String message) {
        super(message);
    }

    public UIANotConnected(Throwable t) {
        super(t);
    }

    public UIANotConnected(String message, Throwable t) {
        super(message, t);
    }
}
