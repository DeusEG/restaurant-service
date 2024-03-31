package com.deus.restaurantservice.service;

import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.model.TableData;
import com.deus.restaurantservice.repository.RoleRepository;
import com.deus.restaurantservice.repository.TableDataRepository;
import com.deus.restaurantservice.service.impl.TableDataServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
class TableDataServiceTest {

    @Autowired
    EntityManager entityManager;
    @Mock
    TableDataRepository tableDataRepository;

    TableDataServiceImpl tableDataService;

    @BeforeEach
    void setUp() {
        tableDataService = new TableDataServiceImpl(tableDataRepository);
    }

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }

    @Test
    void test_get_all_tables_by_restaurant() {
        var restaurant = new Restaurant();
        when(tableDataRepository.getAllByRestaurant(restaurant)).thenReturn(List.of(new TableData(), new TableData()));

        var tables = tableDataService.getAllTableByRestaurant(restaurant);

        assertEquals(2, tables.size());
    }

    @Test
    void test_get_table_by_id() {
        var tableId = 1L;
        var table = new TableData();
        when(tableDataRepository.getTableDataById(tableId)).thenReturn(table);

        var retrievedTable = tableDataService.getTableById(tableId);

        assertNotNull(retrievedTable);
        assertEquals(table, retrievedTable);
    }
}