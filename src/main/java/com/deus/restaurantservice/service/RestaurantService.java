package com.deus.restaurantservice.service;

import com.deus.restaurantservice.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> getAllRestaurant();
    Restaurant getRestaurantById(Long id);
}
