package com.deus.restaurantservice.service;

import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.model.TableData;

import java.util.List;

public interface TableDataService {
    List<TableData> getAllTableByRestaurant(Restaurant restaurant);
    TableData getTableById(Long id);
}
