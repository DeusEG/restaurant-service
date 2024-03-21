package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/show")
    public String getAllRestaurantInfo(Model model){
        model.addAttribute("restaurant", restaurantService.getAllRestaurant());
        return "restaurant-info-list";
    }
}
