package com.deus.restaurantservice.service;

import com.deus.restaurantservice.exception.DeleteModerException;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.repository.RoleRepository;
import com.deus.restaurantservice.repository.UserRepository;
import com.deus.restaurantservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserServiceTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    EntityManager entityManager;
    UserService userService;

    @BeforeEach
    void setUp() {
        var passwordEncoder = new BCryptPasswordEncoder();
        userService = new UserServiceImpl(userRepository, roleRepository, passwordEncoder);
    }

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }

    @Test
    void test_get_all_user() {
        List<User> users = userService.getAllUser();
        assertEquals(4, users.size());
    }

    @Test
    void test_find_by_telegram() {
        String telegram = "aaa";
        var user = userService.findByTelegram(telegram);
        var role = roleRepository.findByName("USER");
        var expectedUser = new User(3L, "aaa", "aaa",
                role, "$2a$10$H2u5Ljr9Xp2ACuVEbmjaLuLTsy99GjG36zoKjiiq0b0I0O8WIc4t6");
        assertEquals(expectedUser, user);
    }

    @Test
    void test_create_user() {
        var name = "user";
        var telegram = "telegram";
        var password = "password";

        var user = userService.createUser(name, telegram, password);
        assertEquals(name, user.getName());
        assertEquals(telegram, user.getTelegram());
    }

    @Test
    void test_delete_user() {
        var telegram = "qqq";
        var result = userService.deleteByTelegram(telegram);
        assertEquals(1, result);
    }

    @Test
    void test_delete_moder_role() {
        String telegram = "vvv";
        assertThrows(DeleteModerException.class, () -> userService.deleteByTelegram(telegram));
    }

}