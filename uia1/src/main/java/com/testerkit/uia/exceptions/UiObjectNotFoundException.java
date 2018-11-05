package com.testerkit.uia.exceptions;

public class UiObjectNotFoundException  extends Exception{
    private static final long serialVersionUID = 1L;

    /**
     * @since API Level 16
     **/
    public UiObjectNotFoundException(String msg) {
        super(msg);
    }

    /**
     * @since API Level 16
     **/
    public UiObjectNotFoundException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    /**
     * @since API Level 16
     **/
    public UiObjectNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
