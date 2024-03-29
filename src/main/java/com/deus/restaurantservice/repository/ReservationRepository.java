package com.deus.restaurantservice.repository;

import com.deus.restaurantservice.model.Reservation;
import com.deus.restaurantservice.model.TableData;
import com.deus.restaurantservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByUser(User user);
    List<Reservation> findAllByTable(TableData table);
}
