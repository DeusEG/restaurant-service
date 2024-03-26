package com.deus.restaurantservice.service;

import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.model.TableData;
import com.deus.restaurantservice.repository.TableDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableDataService {
    private final TableDataRepository tableDataRepository;

    public TableDataService(TableDataRepository tableDataRepository) {
        this.tableDataRepository = tableDataRepository;
    }

    public List<TableData> getAllTableByRestaurant(Restaurant restaurant) {
        return tableDataRepository.getAllByRestaurant(restaurant);
    }

    public TableData getTableById(Long id) {
        return tableDataRepository.getTableDataById(id);
    }

}
