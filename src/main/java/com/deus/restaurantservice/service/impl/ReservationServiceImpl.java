package com.deus.restaurantservice.service.impl;

import com.deus.restaurantservice.exception.IncorrectRegistrationDataException;
import com.deus.restaurantservice.exception.IncorrectReservationException;
import com.deus.restaurantservice.model.Reservation;
import com.deus.restaurantservice.model.TableData;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.repository.ReservationRepository;
import com.deus.restaurantservice.service.ReservationService;
import com.deus.restaurantservice.utils.DateTimeUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис для работы с бронированиями
 */
@Service
public class ReservationServiceImpl implements ReservationService {
    private static final String DATE_TIME_RESERVATION_ERROR_MESSAGE = "Некорректные дата и/или время";
    private static final String RESERVATION_ALREADY_EXIST_ERROR_MESSAGE = "Это время занято, пожалуйста, выберите другое время";
    private static final String NUMBER_OF_SEATS_ERROR_MESSAGE = "Вы не выбрали количество мест или оно меньше, чем вам нужно";
    private final ReservationRepository reservationRepository;
    private final TableDataServiceImpl tableDataServiceImpl;

    public ReservationServiceImpl(ReservationRepository reservationRepository, TableDataServiceImpl tableDataServiceImpl) {
        this.reservationRepository = reservationRepository;
        this.tableDataServiceImpl = tableDataServiceImpl;
    }

    /**
     * Метод для получения всех бронирований пользователя
     *
     * @return Список бронирований пользователя
     */
    @Override
    public List<Reservation> getAllReservationByUser(User user) {
        return reservationRepository.findAllByUser(user);
    }

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
    @Override
    public Reservation createReservation(User user, Long table, String date, String time,
                                         String comment, Integer numberOfSeats) {
        var dateTime = convertAndValidateDateTime(date, time);
        var tableData = checkAlreadyExistReservations(dateTime, table);
        if (numberOfSeats == null || tableData.getNumberOfSeats() < numberOfSeats) {
            throw new IncorrectReservationException(NUMBER_OF_SEATS_ERROR_MESSAGE);
        }

        var reservation = new Reservation(user, tableData, dateTime, comment, numberOfSeats);
        reservationRepository.save(reservation);
        return reservation;
    }

    /**
     * Метод для конвертации и валидации даты и времени
     *
     * @param date          Дата бронирования типа String
     * @param time          Время бронирвоания типа String
     * @return              Возвращает дату и время типа LocalDateTime
     * @throws IncorrectRegistrationDataException если время не указано или оно уже прошло
     */
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

    /**
     * Метод для проверки существующих броинрований
     *
     * @param dateTime   Дата и время
     * @param table      Стол для бронирвоания
     * @return           Возвращает объект типа TableData
     * @throws IncorrectRegistrationDataException если уже существует бронирование, за один час и после
     */
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
