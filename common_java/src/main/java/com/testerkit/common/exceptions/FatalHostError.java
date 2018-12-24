package com.testerkit.common.exceptions;

/**
 * An exception that indicates a fatal unrecoverable error has occurred on the host machine
 * running TradeFederation, and that the TradeFederation instance should be shut down.
 */
@SuppressWarnings("serial")
public class FatalHostError extends RuntimeException {

    /**
     * Creates a {@link FatalHostError}.
     *
     * @param msg the detailed message
     * @param cause the original cause of the fatal host error.
     *
     * @see {@link RuntimeException#RuntimeException(String, Throwable)}
     */
    public FatalHostError(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * Creates a {@link FatalHostError}.
     *
     * @param msg the detailed message
     *
     * @see {@link RuntimeException#RuntimeException(String)}
     */
    public FatalHostError(String msg) {
        super(msg);
    }
}
