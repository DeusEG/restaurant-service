package com.deus.restaurantservice.repository;

import com.deus.restaurantservice.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant getRestaurantById(Long id);
    Restaurant findRestaurantByAdmin_UserId(Long userId);
}
