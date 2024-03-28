package com.deus.restaurantservice.service;

import com.deus.restaurantservice.exception.IncorrectCommentLengthException;
import com.deus.restaurantservice.model.*;
import com.deus.restaurantservice.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private static final String COMMENT_ERROR_MESSAGE = "Длина комментария должна быть от %d до %d";
    private final CommentRepository commentRepository;
    private static final Integer MIN_COMMENT_LENGTH = 5;
    private static final Integer MAX_COMMENT_LENGTH = 300;


    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllCommentsByRestaurant(Restaurant restaurant) {
        return commentRepository.findAllByRestaurant(restaurant);
    }

    public Comment createComment(User user, Restaurant restaurant, String commentText) {
        if (commentText.length() < 5 || commentText.length() > 300) {
            throw new IncorrectCommentLengthException(
                    String.format(COMMENT_ERROR_MESSAGE, MIN_COMMENT_LENGTH, MAX_COMMENT_LENGTH));
        }
        var comment = new Comment(user, restaurant, commentText);
        commentRepository.save(comment);
        return comment;
    }
}
