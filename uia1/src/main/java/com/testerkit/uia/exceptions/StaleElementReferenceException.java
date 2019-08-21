package com.testerkit.uia.exceptions;

import com.testerkit.common.exceptions.UIAException;

public class StaleElementReferenceException extends UIAException {
    private static final long serialVersionUID = -5835005031770654071L;

    public StaleElementReferenceException(String message) {
        super(message);
    }

    public StaleElementReferenceException(Throwable t) {
        super(t);
    }

    public StaleElementReferenceException(String message, Throwable t) {
        super(message, t);
    }
}
