package com.deus.restaurantservice.service;

import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.repository.RoleRepository;
import com.deus.restaurantservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, RoleRepository roleRepository1, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository1;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User findByTelegram(String telegram) {
        return userRepository.findByTelegram(telegram);
    }


    @Transactional
    public String deleteByTelegram(String telegram) {
        return userRepository.removeByTelegram(telegram);
    }

    public User createUser(String name, String telegram, String password) {
        User newUser =  new User(name, telegram, roleRepository.findByName("USER"),
                passwordEncoder.encode(password));
        userRepository.save(newUser);
        return newUser;
    }
}
