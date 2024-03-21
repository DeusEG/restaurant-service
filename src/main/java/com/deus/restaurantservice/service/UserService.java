package com.deus.restaurantservice.service;

import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.repository.RestaurantRepository;
import com.deus.restaurantservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
