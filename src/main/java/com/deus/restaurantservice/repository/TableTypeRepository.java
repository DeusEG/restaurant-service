package com.deus.restaurantservice.repository;

import com.deus.restaurant.model.tabletype.TableType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableTypeRepository extends JpaRepository<TableType, Long> {
}
