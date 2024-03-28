package com.deus.restaurantservice.service;

import com.deus.restaurantservice.exception.IncorrectDateTimeException;
import com.deus.restaurantservice.model.Reservation;
import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.model.TableData;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.repository.ReservationRepository;
import com.deus.restaurantservice.repository.RestaurantRepository;
import com.deus.restaurantservice.utils.DateTimeUtils;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TableDataService tableDataService;

    public ReservationService(ReservationRepository reservationRepository, TableDataService tableDataService) {
        this.reservationRepository = reservationRepository;
        this.tableDataService = tableDataService;
    }

    public List<Reservation> getAllReservationByUser(User user) {
        return reservationRepository.findAllByUser(user);
    }

    public Reservation createReservation(User user, Long table, String date, String time, String comment) {
        var dateTime = DateTimeUtils.convertStringToLocalDateTime(date, time);
        var tableData = tableDataService.getTableById(table);
        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new IncorrectDateTimeException("Дата и/или время уже прошла");
        }
        Reservation reservation =  new Reservation(user, tableData, dateTime, comment);
        reservationRepository.save(reservation);
        return reservation;
    }
}
