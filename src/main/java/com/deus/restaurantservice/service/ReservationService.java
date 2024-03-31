package com.deus.restaurantservice.service;

import com.deus.restaurantservice.exception.IncorrectRegistrationDataException;
import com.deus.restaurantservice.model.Reservation;
import com.deus.restaurantservice.model.User;

import java.util.List;

/**
 * Сервис для работы с бронированиями
 */
public interface ReservationService {

    /**
     * Метод для получения всех бронирований пользователя
     *
     * @return Список бронирований пользователя
     */
    List<Reservation> getAllReservationByUser(User user);

    /**
     * Метод для создания бронирования
     *
     * @param user          Пользователь, осуществляющий бронирование
     * @param table         Идентификатор стола, который броинрует пользователь
     * @param date          Дата бронирования
     * @param time          Время бронирвоания
     * @param comment       Комментарий к бронированию
     * @param numberOfSeats Количетсво гостей для бронирования
     * @return              Бронирование
     * @throws IncorrectRegistrationDataException если количество мест равно нулю или меньше, чем есть у стола
     */
    Reservation createReservation(User user, Long table, String date, String time,
                                  String comment, Integer numberOfSeats);
}
