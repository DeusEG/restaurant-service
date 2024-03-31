package com.deus.restaurantservice.service;

import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.model.TableData;

import java.util.List;

/**
 * Сервис для работы со столами
 */
public interface TableDataService {

    /**
     * Метод для получения всех столов в ресторане
     *
     * @param restaurant    Ресторан
     * @return              Список столов ресторана
     */
    List<TableData> getAllTableByRestaurant(Restaurant restaurant);

    /**
     * Метод для получение стола по его идентификтаору
     *
     * @param id Идентификатор стола
     * @return   Объект типа TableData
     */
    TableData getTableById(Long id);
}
