package com.deus.restaurantservice.service.impl;

import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.model.TableData;
import com.deus.restaurantservice.repository.TableDataRepository;
import com.deus.restaurantservice.service.TableDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableDataServiceImpl implements TableDataService {
    private final TableDataRepository tableDataRepository;

    public TableDataServiceImpl(TableDataRepository tableDataRepository) {
        this.tableDataRepository = tableDataRepository;
    }

    @Override
    public List<TableData> getAllTableByRestaurant(Restaurant restaurant) {
        return tableDataRepository.getAllByRestaurant(restaurant);
    }

    @Override
    public TableData getTableById(Long id) {
        return tableDataRepository.getTableDataById(id);
    }

}
