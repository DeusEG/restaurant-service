package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Контроллер аутентификации пользователей. Отвечает аутентификацию и авторизацию пользователей
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод для отображения страницы входа
     *
     * @return      Страница входа
     */
    @GetMapping()
    public String getLogin() {
        return "/login";
    }

    /**
     * Метод авторизации пользователей
     *
     * @param session Позволяет хранить и извлекать атрибуты, связанные с сеансом или пользователем, пока длится сессия
     * @return        Перенаправление на страницу модератора, администратора или на страницу для пользователей
     */
    @GetMapping("/authorization")
    public String authorization(HttpSession session) {
        var user = userService.findByTelegram(SecurityContextHolder.getContext().getAuthentication().getName());
        session.setAttribute("user", user);
        if (user.getRole().getName().equals("MODER")) {
            return "redirect:/moder";
        } else if (user.getRole().getName().equals("ADMIN")) {
            return "redirect:/admin";
        }
        return "redirect:/restaurant/show";
    }
}
