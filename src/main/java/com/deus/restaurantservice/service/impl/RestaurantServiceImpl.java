package com.deus.restaurantservice.service.impl;

import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.repository.RestaurantRepository;
import com.deus.restaurantservice.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.getRestaurantById(id);
    }

    @Override
    public Restaurant getRestaurantByAdmin(Long id) {
        return restaurantRepository.findRestaurantByAdmin_UserId(id);
    }
}
