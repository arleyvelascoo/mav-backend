package com.example.mavbackend.exception;

/**
 * Customized Exception for validations
 */

public class MAVValidationException extends RuntimeException {
    private static final long serialVersionUID = 2573447829740708982L;

    /**
     * Default message
     */
    public MAVValidationException() {
        super("La información no cumple con las validaciones");
    }

    /**
     * Customized message
     *
     * @param message -
     */
    public MAVValidationException(String message) {
        super(message);
    }

    public MAVValidationException(String message, boolean continuePossibility){
        super(message, continuePossibility ? new Throwable("true") : null);
    }

    /**
     * Customized message and exception to rethrow
     * @param message -
     * @param cause -
     */
    public MAVValidationException(String message, Throwable cause){
        super(message, cause);
    }

    public MAVValidationException(String message, Exception exception){
        super(message, exception);
    }

    public MAVValidationException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
