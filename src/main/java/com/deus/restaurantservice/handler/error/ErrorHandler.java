package com.deus.restaurantservice.handler.error;

import com.deus.restaurantservice.exception.IncorrectDateTimeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({IncorrectDateTimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleItemNotAvailable(final RuntimeException e, Model model) {
        return "error";
    }
}
