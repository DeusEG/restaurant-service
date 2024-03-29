package com.deus.restaurantservice.exception;

public class DeleteModerException extends RuntimeException {
    public DeleteModerException() {
        super();
    }

    public DeleteModerException(String message) {
        super(message);
    }

    public DeleteModerException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteModerException(Throwable cause) {
        super(cause);
    }
}
