package com.deus.restaurantservice.service.impl;

import com.deus.restaurantservice.exception.IncorrectReservationException;
import com.deus.restaurantservice.model.Reservation;
import com.deus.restaurantservice.model.TableData;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.repository.ReservationRepository;
import com.deus.restaurantservice.service.ReservationService;
import com.deus.restaurantservice.service.RestaurantService;
import com.deus.restaurantservice.utils.DateTimeUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private static final String DATE_TIME_RESERVATION_ERROR_MESSAGE = "Некорректные дата и/или время";
    private static final String RESERVATION_ALREADY_EXIST_ERROR_MESSAGE = "Это время занято, пожалуйста, выберите другое время";
    private static final String NUMBER_OF_SEATS_ERROR_MESSAGE = "Выберите количество мест для бронирования";
    private final ReservationRepository reservationRepository;
    private final TableDataServiceImpl tableDataServiceImpl;

    public ReservationServiceImpl(ReservationRepository reservationRepository, TableDataServiceImpl tableDataServiceImpl) {
        this.reservationRepository = reservationRepository;
        this.tableDataServiceImpl = tableDataServiceImpl;
    }

    @Override
    public List<Reservation> getAllReservationByUser(User user) {
        return reservationRepository.findAllByUser(user);
    }

    @Override
    public Reservation createReservation(User user, Long table, String date, String time,
                                         String comment, Integer numberOfSeats) {
        if (numberOfSeats == null) {
            throw new IncorrectReservationException(NUMBER_OF_SEATS_ERROR_MESSAGE);
        }
        var dateTime = convertAndValidateDateTime(date, time);
        var tableData = checkAlreadyExistReservations(dateTime, table);

        var reservation = new Reservation(user, tableData, dateTime, comment, numberOfSeats);
        reservationRepository.save(reservation);
        return reservation;
    }

    private LocalDateTime convertAndValidateDateTime(String date, String time) {
        if (date.isEmpty() || time.isEmpty()) {
            throw new IncorrectReservationException(DATE_TIME_RESERVATION_ERROR_MESSAGE);
        }
        var dateTime = DateTimeUtils.convertStringToLocalDateTime(date, time);
        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new IncorrectReservationException(DATE_TIME_RESERVATION_ERROR_MESSAGE);
        }
        return dateTime;
    }

    private TableData checkAlreadyExistReservations(LocalDateTime dateTime, Long table) {
        var tableData = tableDataServiceImpl.getTableById(table);
        var allReservationByTable = reservationRepository.findAllByTable(tableData);
        for (Reservation reservation : allReservationByTable) {
            var existingDateTime = reservation.getDateTime();
            var existingDateTimeOneHourBefore = existingDateTime.minusHours(1);
            var existingDateTimeOneHourLater = existingDateTime.plusHours(1);

            if (dateTime.isAfter(existingDateTimeOneHourBefore) && dateTime.isBefore(existingDateTimeOneHourLater)) {
                throw new IncorrectReservationException(RESERVATION_ALREADY_EXIST_ERROR_MESSAGE);
            }
        }
        return tableData;
    }

}
