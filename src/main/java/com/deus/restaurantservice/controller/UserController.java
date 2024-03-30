package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Контроллер для пользователей. Управляет получением и изменением данных пользователя
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод для получения данных пользователя
     *
     * @param session Позволяет хранить и извлекать атрибуты, связанные с сеансом или пользователем, пока длится сессия
     * @param model   Модель для передачи данных на страницу
     * @return        Страница для отображения данных пользователя
     */
    @GetMapping("/info")
    public String showUserInfo(HttpSession session, Model model) {
        var user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "user-info";
    }

    /**
     * Метод для получения формы для изменения данных о пользователе
     *
     * @param session Позволяет хранить и извлекать атрибуты, связанные с сеансом или пользователем, пока длится сессия
     * @param model   Модель для передачи данных на страницу
     * @return        Форма для изменения данных пользователя
     */
    @GetMapping("/info/change")
    public String changeUserInfo(HttpSession session, Model model) {
        var user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "change-user-info";
    }

    /**
     * Метод для получения формы для изменения данных о пользователе
     *
     * @param session   Позволяет хранить и извлекать атрибуты, связанные с сеансом или пользователем, пока длится сессия
     * @param password  Телеграм пользователя
     * @param name      Имя пользователя
     * @return          Перенаправление на форму аутентификации (в связи со сменой пароля)
     */
    @PatchMapping("/info/change/")
    public String setLinkForbiddenStatus(HttpSession session, String password, String name) {
        var user = (User) session.getAttribute("user");
        userService.updateUser(user, name, password);
        return "redirect:/login";
    }
}
