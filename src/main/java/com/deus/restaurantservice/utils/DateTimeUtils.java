package com.deus.restaurantservice.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    private DateTimeUtils() {
        throw new AssertionError("This class should not be instantiated.");
    }

    public static String getDateFromDateTime(LocalDateTime dateTime) {
        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dateTime.format(formatter);
    }

    public static String getTimeFromDateTime(LocalDateTime dateTime) {
        var formatter = DateTimeFormatter.ofPattern("HH-mm");
        return dateTime.format(formatter);
    }

    public static LocalDateTime convertStringToLocalDateTime(String date, String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate localDate = LocalDate.parse(date, formatter);
        LocalTime localTime = LocalTime.parse(time, timeFormatter);
        return LocalDateTime.of(localDate, localTime);
    }
}
