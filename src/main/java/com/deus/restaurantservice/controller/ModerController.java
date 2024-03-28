package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @DeleteMapping("/{telegram}")
    public String deleteUser(@PathVariable String telegram) {
        userService.deleteByTelegram(telegram);
        return "redirect:/moder";
    }
}
