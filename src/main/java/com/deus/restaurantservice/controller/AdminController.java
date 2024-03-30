package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.service.CommentService;
import com.deus.restaurantservice.service.ReservationService;
import com.deus.restaurantservice.service.RestaurantService;
import com.deus.restaurantservice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Контроллер для пользоваталей с ролью ADMIN. Отвечает за получение и удаление комментариев пользователей
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RestaurantService restaurantService;
    private final CommentService commentService;

    public AdminController(UserService userService, RestaurantService restaurantService, CommentService commentService) {
        this.userService = userService;
        this.restaurantService = restaurantService;
        this.commentService = commentService;
    }

    /**
     * Метод для отображения комментариев ресторана
     *
     * @param model   Модель для передачи данных на страницу
     * @param session Позволяет хранить и извлекать атрибуты, связанные с сеансом или пользователем, пока длится сессия
     * @return        Страница для отображения и редактирования комментариев ресторана
     */
    @GetMapping()
    public String showCommentsByRestaurantForAdminRole(Model model, HttpSession session) {
        var user = (User) session.getAttribute("user");
        if (!user.getRole().getName().equals("ADMIN")) {
            model.addAttribute("errorMessage", "Отказно в доступе");
        }
        var restaurant = restaurantService.getRestaurantByAdmin(user.getUserId());
        model.addAttribute("comments", commentService.getAllCommentsByRestaurant(restaurant));
        return "admin-restaurant-comments";
    }

    /**
     * Метод для удаление комментария по его идентификатору
     *
     * @param commentId Идентификатор комментария
     * @return          Перенаправляет на страницу отображения и редактирования комментариев ресторана
     */
    @DeleteMapping("/{commentId}")
    public String deleteUser(@PathVariable String commentId) {
        commentService.deleteCommentById(commentId);
        return "redirect:/admin";
    }

}
