package com.deus.restaurantservice.repository;

import com.deus.restaurantservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByTelegram(String telegram);
    String removeByTelegram(String telegram);
}
