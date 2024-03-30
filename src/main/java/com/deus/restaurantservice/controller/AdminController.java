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

    @GetMapping()
    public String showCommentsByRestaurantForAdminRole(Model model, HttpSession session) {
        var user = (User) session.getAttribute("user");
        var restaurant = restaurantService.getRestaurantByAdmin(user.getUserId());
        model.addAttribute("comments", commentService.getAllCommentsByRestaurant(restaurant));
        return "admin-restaurant-comments";
    }

    @DeleteMapping("/{commentId}")
    public String deleteUser(@PathVariable String commentId) {
        commentService.deleteCommentById(commentId);
        return "redirect:/admin";
    }

}
