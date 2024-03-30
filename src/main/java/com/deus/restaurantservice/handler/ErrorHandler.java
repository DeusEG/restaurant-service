package com.deus.restaurantservice.handler;

import com.deus.restaurantservice.exception.DeleteModerException;
import com.deus.restaurantservice.exception.IncorrectCommentLengthException;
import com.deus.restaurantservice.exception.IncorrectReservationException;
import com.deus.restaurantservice.exception.IncorrectRegistrationDataException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({IncorrectReservationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIncorrectDateTime(final IncorrectReservationException e, Model model) {
        model.addAttribute("exception", e);
        return "error";
    }

    @ExceptionHandler({IncorrectRegistrationDataException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIncorrectRegistration(final IncorrectRegistrationDataException e, Model model) {
        model.addAttribute("exception", e);
        return "error";
    }

    @ExceptionHandler({IncorrectCommentLengthException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIncorrectCommentLength(final IncorrectCommentLengthException e, Model model) {
        model.addAttribute("exception", e);
        return "error";
    }

    @ExceptionHandler({DeleteModerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIncorrectDateTime(final DeleteModerException e, Model model) {
        model.addAttribute("exception", e);
        return "error";
    }
}
