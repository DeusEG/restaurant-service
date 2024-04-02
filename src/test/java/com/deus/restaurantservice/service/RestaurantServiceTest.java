package com.deus.restaurantservice.service;

import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.repository.RestaurantRepository;
import com.deus.restaurantservice.service.impl.RestaurantServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {
    @Mock
    EntityManager entityManager;
    @Mock
    RestaurantRepository restaurantRepository;
    RestaurantServiceImpl restaurantService;

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
        var mockRestaurants = List.of(new Restaurant(), new Restaurant());
        when(restaurantRepository.findAll()).thenReturn(mockRestaurants);
        var restaurants = restaurantService.getAllRestaurant();

        assertEquals(2, restaurants.size());
    }

    @Test
    void test_get_restaurant_by_id() {
        var restaurantId = 1L;
        var mockRestaurant = new Restaurant();
        when(restaurantRepository.getRestaurantById(restaurantId)).thenReturn(mockRestaurant);

        var retrievedRestaurant = restaurantService.getRestaurantById(restaurantId);

        assertNotNull(retrievedRestaurant);
        assertEquals(mockRestaurant, retrievedRestaurant);
    }

    @Test
    void test_get_restaurant_by_admin() {
        var adminId = 1L;
        var mockRestaurant = new Restaurant();
        when(restaurantRepository.findRestaurantByAdmin_UserId(adminId)).thenReturn(mockRestaurant);

        var retrievedRestaurant = restaurantService.getRestaurantByAdmin(adminId);

        assertNotNull(retrievedRestaurant);
        assertEquals(mockRestaurant, retrievedRestaurant);
    }
}