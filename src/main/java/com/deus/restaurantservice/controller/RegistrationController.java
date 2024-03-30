package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер для регистрации пользователей.
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод для отображения страницы регистрации
     *
     * @return      Страница регистрации
     */
    @GetMapping()
    public String showRegistration() {
        return "/registration";
    }


    /**
     * Метод для регистрации пользователя
     *
     * @param name      Имя пользователя
     * @param telegram  Телеграм пользователя
     * @param password  Пароль пользователя
     * @return          Если пользователь уже существует, возвращает на страницу регистрации,
     *                  иначе перенапрвляет на страницу аутентификации
     */
    @PostMapping()
    public String registerUser(String name, String telegram, String password) {
        if (userService.findByTelegram(telegram) != null) {
            return "/registration";
        }
        userService.createUser(name, telegram, password);
        return "redirect:/login";
    }
}
