package com.deus.restaurantservice.service;

import com.deus.restaurantservice.exception.IncorrectCommentLengthException;
import com.deus.restaurantservice.model.Comment;
import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.repository.CommentRepository;
import com.deus.restaurantservice.repository.RoleRepository;
import com.deus.restaurantservice.repository.UserRepository;
import com.deus.restaurantservice.service.impl.CommentServiceImpl;
import com.deus.restaurantservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
class CommentServiceTest {
    @Mock
    CommentRepository commentRepository;
    CommentServiceImpl commentService;
    @Autowired
    EntityManager entityManager;

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
        var restaurant = new Restaurant();
        when(commentRepository.findAllByRestaurant(restaurant)).thenReturn(List.of(new Comment(), new Comment()));

        var comments = commentService.getAllCommentsByRestaurant(restaurant);

        assertEquals(2, comments.size());
    }

    @Test
    void test_create_comment_with_valid_data() {
        var user = new User();
        var restaurant = new Restaurant();
        var commentText = "comment";

        when(commentRepository.save(any(Comment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var comment = commentService.createComment(user, restaurant, commentText);

        assertNotNull(comment);
        assertEquals(user, comment.getUser());
        assertEquals(restaurant, comment.getRestaurant());
        assertEquals(commentText, comment.getComment());
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void test_create_comment_with_exception() {
        var user = new User();
        var restaurant = new Restaurant();
        var commentText = "12";

        assertThrows(IncorrectCommentLengthException.class, () -> commentService.createComment(user, restaurant, commentText));
        verify(commentRepository, never()).save(any(Comment.class));
    }

    @Test
    void test_delete_comment() {
        var commentId = "1";
        when(commentRepository.removeById(anyLong())).thenReturn(1L);

        var deletedCommentId = commentService.deleteCommentById(commentId);

        assertEquals(1L, deletedCommentId);
        verify(commentRepository, times(1)).removeById(anyLong());
    }
}