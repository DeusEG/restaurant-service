package com.deus.restaurantservice.service;

import com.deus.restaurantservice.exception.IncorrectReservationException;
import com.deus.restaurantservice.model.Reservation;
import com.deus.restaurantservice.model.TableData;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.repository.ReservationRepository;
import com.deus.restaurantservice.service.impl.ReservationServiceImpl;
import com.deus.restaurantservice.service.impl.TableDataServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
class ReservationServiceTest {
    @Autowired
    EntityManager entityManager;
    @Mock
    ReservationRepository reservationRepository;
    @Mock
    TableDataServiceImpl tableDataServiceImpl;

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }

    @Test
    void test_create_reservation() {
        var reservationService = new ReservationServiceImpl(reservationRepository, tableDataServiceImpl);
        var user = new User();
        var tableId = 1L;
        var date = "2024-04-04";
        var time = "19:00";
        var comment = "comment";
        var numberOfSeats = 4;

        var tableData = new TableData();
        tableData.setNumberOfSeats(6);

        var dateTime = LocalDateTime.of(2024, 4, 4, 19, 0);

        when(tableDataServiceImpl.getTableById(tableId)).thenReturn(tableData);

        var createdReservation = reservationService.createReservation(user, tableId, date, time, comment, numberOfSeats);

        assertNotNull(createdReservation);
        assertEquals(user, createdReservation.getUser());
        assertEquals(tableData, createdReservation.getTable());
        assertEquals(dateTime, createdReservation.getDateTime());
        assertEquals(comment, createdReservation.getComment());
        assertEquals(numberOfSeats, createdReservation.getNumberOfSeats());
    }

    @Test
    void test_create_with_incorrect_number_of_seats() {
        var reservationService = new ReservationServiceImpl(reservationRepository, tableDataServiceImpl);
        var user = new User();
        var tableId = 1L;
        var date = "2024-01-30";
        var time = "18:00";
        var comment = "Test comment";
        var numberOfSeats = 8;

        var tableData = new TableData();
        tableData.setNumberOfSeats(6);

        when(tableDataServiceImpl.getTableById(tableId)).thenReturn(tableData);


        assertThrows(IncorrectReservationException.class, () -> {
            reservationService.createReservation(user, tableId, date, time, comment, numberOfSeats);
        });
    }

    @Test
    void test_get_reservation_by_user() {
        var reservationService = new ReservationServiceImpl(reservationRepository, tableDataServiceImpl);
        var user = new User();
        var firstReservation = new Reservation();
        var secondReservation = new Reservation();
        var expectedReservations = List.of(firstReservation, secondReservation);

        when(reservationRepository.findAllByUser(user)).thenReturn(expectedReservations);

        var actualReservations = reservationService.getAllReservationByUser(user);

        assertNotNull(actualReservations);
        assertEquals(expectedReservations.size(), actualReservations.size());
        assertTrue(actualReservations.containsAll(expectedReservations));
    }

    @Test
    void test_check_reservation_already_exist() {
        var reservationService = new ReservationServiceImpl(reservationRepository, tableDataServiceImpl);
        var dateTime = LocalDateTime.of(2024, 4, 1, 18, 0);
        var tableId = 1L;

        var tableData = new TableData();
        tableData.setNumberOfSeats(4);

        when(tableDataServiceImpl.getTableById(tableId)).thenReturn(tableData);
        when(reservationRepository.findAllByTable(tableData)).thenReturn(Collections.emptyList());

        var resultTableData = reservationService.checkAlreadyExistReservations(dateTime, tableId);

        assertNotNull(resultTableData);
        assertEquals(tableData, resultTableData);
    }

    @Test
    void test_check_reservation_already_exist_with_throw_exception() {
        var reservationService = new ReservationServiceImpl(reservationRepository, tableDataServiceImpl);
        var dateTime = LocalDateTime.of(2024, 4, 1, 18, 0);
        var tableId = 1L;
        var tableData = new TableData();
        tableData.setNumberOfSeats(4);
        var existingReservation = new Reservation();
        existingReservation.setDateTime(LocalDateTime.of(2024, 4, 1, 17, 30));

        when(tableDataServiceImpl.getTableById(tableId)).thenReturn(tableData);
        when(reservationRepository.findAllByTable(tableData)).thenReturn(Collections.singletonList(existingReservation));

        assertThrows(IncorrectReservationException.class, () -> {
            reservationService.checkAlreadyExistReservations(dateTime, tableId);
        });
    }
}