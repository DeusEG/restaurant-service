package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.exception.IncorrectRegistrationData;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Контроллер для пользоваталей с ролью MODER. Отвечает за получение и удаление пользователей
 */
@Controller
@RequestMapping("/moder")
public class ModerController {

    private final UserService userService;

    public ModerController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод для всех пользователей
     *
     * @param model   Модель для передачи данных на страницу
     * @param session Позволяет хранить и извлекать атрибуты, связанные с сеансом или пользователем, пока длится сессия
     * @return        Страница для отображения всех пользователей
     */
    @GetMapping()
    public String showModerPanel(Model model, HttpSession session) {
        var user = (User) session.getAttribute("user");
        if (!user.getRole().getName().equals("MODER")) {
            model.addAttribute("errorMessage", "Отказно в доступе");
        }
        model.addAttribute("users", userService.getAllUser());
        return "show-users";
    }

    /**
     * Метод для удаления пользователей по телеграму
     *
     * @param telegram Поле телеграм сущности пользователь
     * @return         Перенапрвляет на страницу отображения всех пользователей
     */
    @DeleteMapping("/{telegram}")
    public String deleteUser(@PathVariable String telegram) {
        userService.deleteByTelegram(telegram);
        return "redirect:/moder";
    }
}
