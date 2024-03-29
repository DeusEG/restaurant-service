package com.deus.restaurantservice.service;

import com.deus.restaurantservice.exception.IncorrectDateTimeException;
import com.deus.restaurantservice.model.Reservation;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.repository.ReservationRepository;
import com.deus.restaurantservice.utils.DateTimeUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {
    private static final String RESERVATION_ERROR_MESSAGE = "Дата и/или время уже прошли";
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
        var dateTime = DateTimeUtils.convertStringToLocalDateTime(date, time);
        var tableData = tableDataService.getTableById(table);
        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new IncorrectDateTimeException(RESERVATION_ERROR_MESSAGE);
        }
        Reservation reservation =  new Reservation(user, tableData, dateTime, comment, Integer.parseInt(numberOfSeats));
        reservationRepository.save(reservation);
        return reservation;
    }
}
