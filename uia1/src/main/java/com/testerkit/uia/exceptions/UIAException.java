package com.testerkit.uia.exceptions;

/**
 * Created by able on 2018/2/11.
 */

public class UIAException extends RuntimeException {
    private static final long serialVersionUID = -1592305571101012889L;

    public UIAException(String message) {
        super(message);
    }

    public UIAException(Throwable t) {
        super(t);
    }

    public UIAException(String message, Throwable t) {
        super(message, t);
    }
}
