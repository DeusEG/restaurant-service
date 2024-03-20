package com.deus.restaurantservice.repository;

import com.deus.restaurantservice.model.TableData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<TableData, Long> {
}
