package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping()
    public String getLogin() {
        return "/login";
    }

    @GetMapping("/distribution")
    public String distribution(HttpSession http) {
        var user = userService.findByTelegram(SecurityContextHolder.getContext().getAuthentication().getName());
        http.setAttribute("user", user);
        if (user.getRole().getName().equals("MODER")) {
            return "redirect:/moder";
        }
        return "redirect:/restaurant/show";
    }
}
