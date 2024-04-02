package com.deus.restaurantservice.service;

import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.repository.RestaurantRepository;
import com.deus.restaurantservice.repository.UserRepository;
import com.deus.restaurantservice.service.impl.RestaurantServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class RestaurantServiceTest {
    @Autowired
    EntityManager entityManager;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    UserRepository userRepository;
    RestaurantService restaurantService;

    @BeforeEach
    void setUp() {
        restaurantService = new RestaurantServiceImpl(restaurantRepository);
    }

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }

    @Test
    void test_get_all_restaurant() {
        var restaurants = restaurantService.getAllRestaurant();
        assertEquals(2, restaurants.size());
    }

    @Test
    void test_get_restaurant_by_id() {
        var restaurantId = 1L;
        var user = userRepository.findByTelegram("qqq");
        var restaurant2 = new Restaurant(1L, "Первомайский проспект 131", user);
        var restaurant = restaurantService.getRestaurantById(restaurantId);

        assertNotNull(restaurant);
        assertEquals(restaurant2, restaurant);
    }

    @Test
    void test_get_restaurant_by_admin() {
        var user = userRepository.findByTelegram("qqq");
        var restaurant2 = new Restaurant(1L, "Первомайский проспект 131", user);
        var restaurant = restaurantService.getRestaurantByAdmin(1L);

        assertNotNull(restaurant);
        assertEquals(restaurant2, restaurant);
    }
}