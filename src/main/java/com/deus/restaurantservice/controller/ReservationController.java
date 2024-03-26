package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.model.TableData;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.repository.UserRepository;
import com.deus.restaurantservice.service.ReservationService;
import com.deus.restaurantservice.service.RestaurantService;
import com.deus.restaurantservice.service.TableDataService;
import com.deus.restaurantservice.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    @GetMapping("/show")
    public String getAllReservation(HttpSession session, Model model) {
        var reservations = reservationService.getAllReservationByUser((User) session.getAttribute("user"));
        model.addAttribute("reservations", reservations);
        return "show-user-reservation";
    }

    @GetMapping("/{restaurantId}")
    public String showFormNewReservation(@PathVariable Long restaurantId, Model model) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        model.addAttribute("tables", tableDataService.getAllTableByRestaurant(restaurant));
        return "new-reservation";
    }

    @PostMapping("/{restaurantId}")
    public String createNewReservation(String date, String time, String comment, Long table,
                                       HttpSession session) {
        System.out.println("date-->" + date);
        System.out.println("time--->" + time);
        System.out.println("comment-->" + comment);
        System.out.println("table-->" + table);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.parse(time, timeFormatter);
        var tableData = tableDataService.getTableById(table);
        User user = (User) session.getAttribute("user");
        reservationService.createReservation(user, tableData, localDate, localTime, comment);
        return "redirect:/reservation/show";
    }
}
