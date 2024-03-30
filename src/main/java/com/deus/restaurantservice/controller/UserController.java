package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public String showUserInfo(HttpSession session, Model model) {
        var user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "user-info";
    }
    @GetMapping("/info/change")
    public String changeUserInfo(HttpSession session, Model model) {
        var user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "change-user-info";
    }

    @PatchMapping("/info/change/")
    public String setLinkForbiddenStatus(HttpSession session, String password, String name) {
        var user = (User) session.getAttribute("user");
        userService.updateUser(user, name, password);
        return "redirect:/login";
    }
}