package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showRegistration() {
        return "/registration";
    }

    @PostMapping()
    public String registerUser(String name, String telegram, String password) {
        if (userService.findByTelegram(telegram) != null) {
            return "/registration";
        }
        userService.createUser(name, telegram, password);
        return "redirect:/login";
    }
}
