package com.deus.restaurantservice.service.impl;

import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.repository.RestaurantRepository;
import com.deus.restaurantservice.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы с ресторанами
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Метод для получения всех ресторанов
     *
     * @return Список ресторанов
     */
    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    /**
     * Метод для получения ресторана по его идентификатору
     *
     * @param  id   Идентификатор ресторана
     * @return      Объект типа Restaurant
     */
    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.getRestaurantById(id);
    }

    /**
     * Метод для получения ресторана по идентификатору администратора ресторана
     *
     * @param  id   Идентификатор администратора
     * @return Объект типа Restaurant
     */
    @Override
    public Restaurant getRestaurantByAdmin(Long id) {
        return restaurantRepository.findRestaurantByAdmin_UserId(id);
    }
}
