package com.deus.restaurantservice.exception;

public class IncorrectRegistrationData extends RuntimeException {
    public IncorrectRegistrationData() {
        super();
    }

    public IncorrectRegistrationData(String message) {
        super(message);
    }

    public IncorrectRegistrationData(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectRegistrationData(Throwable cause) {
        super(cause);
    }
}
