package com.deus.restaurantservice.repository;


import com.deus.restaurantservice.model.Comment;
import com.deus.restaurantservice.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findCommentById(Long id);
    List<Comment> findAllByRestaurant(Restaurant restaurant);
    Long removeById(Long id);
}
