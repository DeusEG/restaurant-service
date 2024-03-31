package com.deus.restaurantservice.service;

import com.deus.restaurantservice.model.Restaurant;

import java.util.List;

/**
 * Сервис для работы с ресторанами
 */
public interface RestaurantService {

    /**
     * Метод для получения всех ресторанов
     *
     * @return Список ресторанов
     */
    List<Restaurant> getAllRestaurant();

    /**
     * Метод для получения ресторана по его идентификатору
     *
     * @param  id   Идентификатор ресторана
     * @return      Объект типа Restaurant
     */
    Restaurant getRestaurantById(Long id);

    /**
     * Метод для получения ресторана по идентификатору администратора ресторана
     *
     * @param  id   Идентификатор администратора
     * @return Объект типа Restaurant
     */
    Restaurant getRestaurantByAdmin(Long id);
}
