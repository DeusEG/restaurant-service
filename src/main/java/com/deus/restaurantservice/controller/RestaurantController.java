package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.service.CommentService;
import com.deus.restaurantservice.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Контроллер ресторанов. Отвечает за получение ресторанов и комментариев к ним.
 */
@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final CommentService commentService;


    public RestaurantController(RestaurantService restaurantService, CommentService commentService) {
        this.restaurantService = restaurantService;
        this.commentService = commentService;
    }

    /**
     * Метод получения всех ресторанов
     *
     * @param model   Модель для передачи данных на страницу
     * @return        Страница для отображения всех ресторанов
     */
    @GetMapping("/show")
    public String getAllRestaurantInfo(Model model) {
        model.addAttribute("restaurants", restaurantService.getAllRestaurant());
        return "restaurant-info-list";
    }

    /**
     * Метод получения комментариев ресторана
     *
     * @param restaurantId   Идентификатор ресторана
     * @param model          Модель для передачи данных на страницу
     * @return               Страница для отображения всех комментариев
     */
    @GetMapping("/{restaurantId}")
    public String showCommentsByRestaurant(@PathVariable Long restaurantId, Model model) {
        var restaurant = restaurantService.getRestaurantById(restaurantId);
        model.addAttribute("comments", commentService.getAllCommentsByRestaurant(restaurant));
        return "comments";
    }

    /**
     * Метод для создания комментария к ресторану
     *
     * @param restaurantId   Идентификатор ресторана
     * @param comment        Комментарий
     * @param session        Позволяет хранить и извлекать атрибуты, связанные с сеансом или пользователем, пока длится сессия
     * @return               Перенаправление на страницу получение комментариев ресторана
     */
    @PostMapping("/{restaurantId}")
    public String createNewComment(@PathVariable Long restaurantId, String comment,
                                   HttpSession session) {
        var user = (User) session.getAttribute("user");
        var restaurant = restaurantService.getRestaurantById(restaurantId);
        commentService.createComment(user, restaurant, comment);
        return "redirect:/restaurant/{restaurantId}";
    }
}
