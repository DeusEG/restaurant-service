package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.service.ReservationService;
import com.deus.restaurantservice.service.RestaurantService;
import com.deus.restaurantservice.service.TableDataService;
import com.deus.restaurantservice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Контроллер для бронирования. Отвечает за создание и получение бронирований
 */
@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final RestaurantService restaurantService;
    private final TableDataService tableDataService;
    private final UserService userService;


    public ReservationController(ReservationService reservationService, RestaurantService restaurantService, TableDataService tableDataService, UserService userService) {
        this.reservationService = reservationService;
        this.restaurantService = restaurantService;
        this.tableDataService = tableDataService;
        this.userService = userService;
    }

    /**
     * Метод для отображения бронирований пользователя
     *
     * @param session Позволяет хранить и извлекать атрибуты, связанные с сеансом или пользователем, пока длится сессия
     * @param model   Модель для передачи данных на страницу
     * @return        Страница для отображения бронирований пользователя
     */
    @GetMapping("/show")
    public String getAllReservation(HttpSession session, Model model) {
        var reservations = reservationService.getAllReservationByUser((User) session.getAttribute("user"));
        model.addAttribute("reservations", reservations);
        return "show-user-reservation";
    }

    /**
     * Метод для отображения формы создания нового бронирования
     *
     * @param restaurantId Идентификатор ресторана
     * @param model        Модель для передачи данных на страницу
     * @return             Страница для отображения формы создания нового бронирования
     */
    @GetMapping("/{restaurantId}")
    public String showFormNewReservation(@PathVariable Long restaurantId, Model model) {
        var restaurant = restaurantService.getRestaurantById(restaurantId);
        model.addAttribute("tables", tableDataService.getAllTableByRestaurant(restaurant));
        return "new-reservation";
    }

    /**
     * Метод для создания нового бронирования
     *
     * @param date          Дата бронирования
     * @param time          Время бронирования
     * @param comment       Комментарий бронирования
     * @param table         Стол бронирования
     * @param numberOfSeats Количество мест для бронирования
     * @param session       Позволяет хранить и извлекать атрибуты, связанные с сеансом или пользователем, пока длится сессия
     * @return              Перенаправления на страницу с бронированиями пользователя
     */
    @PostMapping("/{restaurantId}")
    public String createNewReservation(String date, String time, String comment, Long table, Integer numberOfSeats,
                                       HttpSession session) {
        var user = (User) session.getAttribute("user");
        reservationService.createReservation(user, table, date, time, comment, numberOfSeats);
        return "redirect:/reservation/show";
    }
}
