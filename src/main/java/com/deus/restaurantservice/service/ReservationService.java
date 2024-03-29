package com.deus.restaurantservice.service;

import com.deus.restaurantservice.exception.IncorrectDateTimeException;
import com.deus.restaurantservice.model.Reservation;
import com.deus.restaurantservice.model.TableData;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.repository.ReservationRepository;
import com.deus.restaurantservice.utils.DateTimeUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ReservationService {
    private static final String DATE_TIME_RESERVATION_ERROR_MESSAGE = "Дата и/или время уже прошли";
    private static final String RESERVATION_ALREADY_EXIST_ERROR_MESSAGE = "Это время занято, пожалуйста, выберите другое время";
    private final ReservationRepository reservationRepository;
    private final TableDataService tableDataService;

    public ReservationService(ReservationRepository reservationRepository, TableDataService tableDataService) {
        this.reservationRepository = reservationRepository;
        this.tableDataService = tableDataService;
    }

    public List<Reservation> getAllReservationByUser(User user) {
        return reservationRepository.findAllByUser(user);
    }

    public Reservation createReservation(User user, Long table, String date, String time,
                                         String comment, String numberOfSeats) {
        var dateTime = convertAndValidateDateTime(date, time);
        var tableData = checkAlreadyExistReservations(dateTime, table);

        var reservation = new Reservation(user, tableData, dateTime, comment, Integer.parseInt(numberOfSeats));
        reservationRepository.save(reservation);
        return reservation;
    }

    private LocalDateTime convertAndValidateDateTime(String date, String time) {
        if (Objects.isNull(date) || Objects.isNull(time)) {
            throw new IncorrectDateTimeException(DATE_TIME_RESERVATION_ERROR_MESSAGE);
        }
        var dateTime = DateTimeUtils.convertStringToLocalDateTime(date, time);
        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new IncorrectDateTimeException(DATE_TIME_RESERVATION_ERROR_MESSAGE);
        }
        return dateTime;
    }

    private TableData checkAlreadyExistReservations(LocalDateTime dateTime, Long table) {
        var tableData = tableDataService.getTableById(table);
        var allReservationByTable = reservationRepository.findAllByTable(tableData);
        for (Reservation reservation : allReservationByTable) {
            var existingDateTime = reservation.getDateTime();
            var existingDateTimeOneHourLater = existingDateTime.plusHours(2);
            if (dateTime.isBefore(existingDateTimeOneHourLater) && existingDateTime.isBefore(dateTime.plusHours(1))) {
                throw new IncorrectDateTimeException(RESERVATION_ALREADY_EXIST_ERROR_MESSAGE);
            }
        }
        return tableData;
    }

}
