package com.deus.restaurantservice.service.impl;

import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.model.TableData;
import com.deus.restaurantservice.repository.TableDataRepository;
import com.deus.restaurantservice.service.TableDataService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы со столами
 */
@Service
public class TableDataServiceImpl implements TableDataService {
    private final TableDataRepository tableDataRepository;

    public TableDataServiceImpl(TableDataRepository tableDataRepository) {
        this.tableDataRepository = tableDataRepository;
    }

    /**
     * Метод для получения всех столов в ресторане
     *
     * @param restaurant    Ресторан
     * @return              Список столов ресторана
     */
    @Override
    public List<TableData> getAllTableByRestaurant(Restaurant restaurant) {
        return tableDataRepository.getAllByRestaurant(restaurant);
    }

    /**
     * Метод для получение стола по его идентификтаору
     *
     * @param id Идентификатор стола
     * @return   Объект типа TableData
     */
    @Override
    public TableData getTableById(Long id) {
        return tableDataRepository.getTableDataById(id);
    }

}
