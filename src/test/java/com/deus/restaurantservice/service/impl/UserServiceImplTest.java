package com.deus.restaurantservice.service.impl;

import com.deus.restaurantservice.model.Role;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.repository.RoleRepository;
import com.deus.restaurantservice.repository.UserRepository;
import com.deus.restaurantservice.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserServiceImplTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private EntityManager entityManager;
    UserService userService;

    @BeforeEach
    void setUp() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userService = new UserServiceImpl(userRepository, roleRepository, passwordEncoder);

    }

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }

    @Test
    void getAllUser() {
    }

    @Test
    void findByTelegram() {
        String telegram = "aaa";
        var user = userService.findByTelegram(telegram);
        var role = roleRepository.findByName("USER");
        var expectedUser = new User(3L,"aaa", "aaa", role, "$2a$10$H2u5Ljr9Xp2ACuVEbmjaLuLTsy99GjG36zoKjiiq0b0I0O8WIc4t6");
        assertEquals(expectedUser, user);
    }

    @Test
    void deleteByTelegram() {
    }

    @Test
    void createUser() {
    }
}