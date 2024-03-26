package com.deus.restaurantservice.repository;

import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.model.TableData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableDataRepository extends JpaRepository<TableData, Long> {
    List<TableData> getAllByRestaurant(Restaurant restaurant);
    TableData getTableDataById(Long id);
}
