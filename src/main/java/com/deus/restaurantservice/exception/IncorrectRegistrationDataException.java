package com.deus.restaurantservice.exception;

public class IncorrectRegistrationDataException extends RuntimeException {
    public IncorrectRegistrationDataException() {
        super();
    }

    public IncorrectRegistrationDataException(String message) {
        super(message);
    }

    public IncorrectRegistrationDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectRegistrationDataException(Throwable cause) {
        super(cause);
    }
}
