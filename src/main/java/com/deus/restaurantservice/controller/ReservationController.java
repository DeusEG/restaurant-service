package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/show")
    public String getAllReservation(HttpSession session, Model model) {
        var reservations = reservationService.getAllReservationByUser((User) session.getAttribute("user"));
        model.addAttribute("reservations", reservations);
        return "show-user-reservation";
    }
}
