package com.deus.restaurantservice.service;

import com.deus.restaurantservice.model.Reservation;
import com.deus.restaurantservice.model.User;

import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservationByUser(User user);
    Reservation createReservation(User user, Long table, String date, String time,
                                  String comment, Integer numberOfSeats);
}
