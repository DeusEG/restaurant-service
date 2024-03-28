package com.deus.restaurantservice.handler.error;

import com.deus.restaurantservice.exception.IncorrectCommentLengthException;
import com.deus.restaurantservice.exception.IncorrectDateTimeException;
import com.deus.restaurantservice.exception.IncorrectRegistrationData;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({IncorrectDateTimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIncorrectDateTime(final IncorrectDateTimeException e, Model model) {
        model.addAttribute("exception", e);
        return "error";
    }

    @ExceptionHandler({IncorrectRegistrationData.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIncorrectRegistration(final IncorrectRegistrationData e, Model model) {
        model.addAttribute("exception", e);
        return "error";
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIncorrectRegistration(final UsernameNotFoundException e, Model model) {
        model.addAttribute("exception", e);
        return "error";
    }

    @ExceptionHandler({IncorrectCommentLengthException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIncorrectCommentLength(final IncorrectCommentLengthException e, Model model) {
        model.addAttribute("exception", e);
        return "error";
    }
}
