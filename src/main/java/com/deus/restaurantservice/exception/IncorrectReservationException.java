package com.deus.restaurantservice.exception;

public class IncorrectReservationException extends RuntimeException {
    public IncorrectReservationException() {
        super();
    }

    public IncorrectReservationException(String message) {
        super(message);
    }

    public IncorrectReservationException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectReservationException(Throwable cause) {
        super(cause);
    }
}
