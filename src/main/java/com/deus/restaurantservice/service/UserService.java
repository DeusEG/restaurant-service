package com.deus.restaurantservice.service;

import com.deus.restaurantservice.model.User;

import java.util.List;

public interface UserService {
    String deleteByTelegram(String telegram);
    User createUser(String name, String telegram, String password);
    List<User> getAllUser();
    User findByTelegram(String telegram);
    User updateUser(User user, String userName, String password);
}
