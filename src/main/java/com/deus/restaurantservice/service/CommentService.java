package com.deus.restaurantservice.service;

import com.deus.restaurantservice.model.Comment;
import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.repository.CommentRepository;

import java.util.List;

public interface CommentService {
    List<Comment> getAllCommentsByRestaurant(Restaurant restaurant);
    Comment createComment(User user, Restaurant restaurant, String commentText);
    Long deleteCommentById(String id);
}
