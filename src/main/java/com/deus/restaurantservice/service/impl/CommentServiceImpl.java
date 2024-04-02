package com.deus.restaurantservice.service.impl;

import com.deus.restaurantservice.exception.DeleteModerException;
import com.deus.restaurantservice.exception.IncorrectCommentLengthException;
import com.deus.restaurantservice.model.*;
import com.deus.restaurantservice.repository.CommentRepository;
import com.deus.restaurantservice.service.CommentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private static final String COMMENT_ERROR_MESSAGE = "Длина комментария должна быть от %d до %d";
    private final CommentRepository commentRepository;
    private static final Integer MIN_COMMENT_LENGTH = 5;
    private static final Integer MAX_COMMENT_LENGTH = 300;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAllCommentsByRestaurant(Restaurant restaurant) {
        return commentRepository.findAllByRestaurant(restaurant);
    }

    @Override
    public Comment createComment(User user, Restaurant restaurant, String commentText) {
        if (commentText.length() < 5 || commentText.length() > 300) {
            throw new IncorrectCommentLengthException(
                    String.format(COMMENT_ERROR_MESSAGE, MIN_COMMENT_LENGTH, MAX_COMMENT_LENGTH));
        }
        var comment = new Comment(user, restaurant, commentText);
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public Comment findCommentById(Long id) {
        return commentRepository.findCommentById(id);
    }

    @Override
    @Transactional
    public Long deleteCommentById(String id) {
        return commentRepository.removeById(Long.parseLong(id));
    }
}
