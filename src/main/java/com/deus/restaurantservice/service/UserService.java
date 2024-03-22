package com.deus.restaurantservice.service;

import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.repository.RoleRepository;
import com.deus.restaurantservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public UserService(UserRepository userRepository, RoleRepository roleRepository1) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository1;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User findByTelegram(String telegram) {
        return userRepository.findByTelegram(telegram);
    }

    public User addUser(String name, String telegram, String password) {
        User newUser =  new User(name, telegram, roleRepository.findByName("USER"), password);
        userRepository.save(newUser);
        return newUser;
    }
}
