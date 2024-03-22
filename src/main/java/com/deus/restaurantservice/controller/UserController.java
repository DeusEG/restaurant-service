package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping()
    public String getRegistration(Model model) {
        return "/registration";
    }

    @PostMapping()
    public String createUser(String name, String telegram, String password) {
        if (userService.fiByTelegram(telegram) != null) {
            return "/registration";
        }
        userService.addUser(name, telegram, password);
        return "redirect:/login";
    }
}
