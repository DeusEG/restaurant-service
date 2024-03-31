package com.deus.restaurantservice.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilsTest {

    @Test
    void testGetDateFromDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 3, 31, 13, 30);
        String expectedDate = "31-03-2024";
        String actualDate = DateTimeUtils.getDateFromDateTime(dateTime);
        assertEquals(expectedDate, actualDate);
    }

    @Test
    void testGetTimeFromDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 3, 31, 13, 30);
        String expectedTime = "13-30";
        String actualTime = DateTimeUtils.getTimeFromDateTime(dateTime);
        assertEquals(expectedTime, actualTime);
    }

    @Test
    void testConvertStringToLocalDateTime() {
        String date = "2024-03-31";
        String time = "13:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 3, 31, 13, 30);
        LocalDateTime actualDateTime = DateTimeUtils.convertStringToLocalDateTime(date, time);
        assertEquals(expectedDateTime, actualDateTime);
    }
}
