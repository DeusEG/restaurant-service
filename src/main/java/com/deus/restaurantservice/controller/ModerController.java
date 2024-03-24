package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/moder")
public class ModerController {

    private final UserService userService;

    public ModerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showModerPanel(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "show-users";
    }

    @GetMapping("/{username}/delete")
    public String deleteUser(@PathVariable String username) {
        String telegram = userService.deleteByTelegram(username);
        System.out.println("Пользователь с телеграмом: " + telegram + " удален");
        return "redirect:/moder";
    }
}
