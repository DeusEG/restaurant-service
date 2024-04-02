package com.deus.restaurantservice.service;

import com.deus.restaurantservice.exception.IncorrectCommentLengthException;
import com.deus.restaurantservice.model.Comment;
import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.repository.CommentRepository;
import com.deus.restaurantservice.repository.RestaurantRepository;
import com.deus.restaurantservice.repository.UserRepository;
import com.deus.restaurantservice.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentServiceTest {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager entityManager;
    CommentService commentService;

    @BeforeEach
    void setUp() {
        commentService = new CommentServiceImpl(commentRepository);
    }

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }

    @Test
    void test_get_all_comment_by_restaurant() {
        var restaurant = restaurantRepository.getRestaurantById(1L);
        var comments = commentService.getAllCommentsByRestaurant(restaurant);

        assertEquals(0, comments.size());
    }

    @Test
    void test_create_comment_with_valid_data() {
        var user = userRepository.findByTelegram("aaa");
        var restaurant = restaurantRepository.getRestaurantById(1L);
        var commentText = "comment";
        var comment = commentService.createComment(user, restaurant, commentText);

        assertEquals(comment, commentService.findCommentById(2L));
    }

    @Test
    void test_create_comment_with_exception() {
        var user = new User();
        var restaurant = new Restaurant();
        var commentText = "12";

        assertThrows(IncorrectCommentLengthException.class,
                () -> commentService.createComment(user, restaurant, commentText));
    }

    @Test
    void test_delete_comment() {
        var user = userRepository.findByTelegram("aaa");
        var restaurant = restaurantRepository.getRestaurantById(1L);
        var commentText = "comment";
        commentService.createComment(user, restaurant, commentText);
        var deletedCommentId = commentService.deleteCommentById("1");

        assertEquals(1L, deletedCommentId);
    }
}