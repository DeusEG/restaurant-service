package com.deus.restaurantservice.exception;

public class IncorrectDateTimeException extends RuntimeException {
    public IncorrectDateTimeException() {
        super();
    }

    public IncorrectDateTimeException(String message) {
        super(message);
    }

    public IncorrectDateTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDateTimeException(Throwable cause) {
        super(cause);
    }
}
