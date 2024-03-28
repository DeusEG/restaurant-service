package com.deus.restaurantservice.exception;

public class IncorrectCommentLengthException extends RuntimeException {
    public IncorrectCommentLengthException() {
        super();
    }

    public IncorrectCommentLengthException(String message) {
        super(message);
    }

    public IncorrectCommentLengthException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectCommentLengthException(Throwable cause) {
        super(cause);
    }
}
