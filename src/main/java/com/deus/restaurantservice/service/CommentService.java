package com.deus.restaurantservice.service;

import com.deus.restaurantservice.model.*;
import com.deus.restaurantservice.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllCommentsByRestaurant(Restaurant restaurant) {
        return commentRepository.findAllByRestaurant(restaurant);
    }

    public Comment createComment(User user, Restaurant restaurant, String commentText) {
        Comment comment =  new Comment(user, restaurant, commentText);
        commentRepository.save(comment);
        return comment;
    }
}
