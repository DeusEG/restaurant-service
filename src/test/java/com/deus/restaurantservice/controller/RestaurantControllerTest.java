package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.security.CustomDetailsService;
import com.deus.restaurantservice.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {
    @MockBean
    private UserService userService;
    @MockBean
    RestaurantService restaurantService;
    @MockBean
    CommentService commentService;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CustomDetailsService customDetailsService;

    @Test
    @WithMockUser(roles = "USER")
    void test_get_all_restaurant() throws Exception {
        mockMvc.perform(get("/restaurant/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("restaurant-info-list"))
                .andExpect(model().attributeExists("restaurants"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void test_show_comments_by_restaurant() throws Exception {
        var restaurant = new Restaurant();
        when(restaurantService.getRestaurantById(1L)).thenReturn(restaurant);

        mockMvc.perform(get("/restaurant/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("comments"))
                .andExpect(model().attributeExists("comments"));

        verify(commentService).getAllCommentsByRestaurant(restaurant);
    }

    @Test
    @WithMockUser(roles = "USER")
    void test_create_comment() throws Exception {
        var user = new User();
        var restaurant = new Restaurant();
        when(restaurantService.getRestaurantById(1L)).thenReturn(restaurant);
        mockMvc.perform(post("/restaurant/1")
                        .sessionAttr("user", user)
                        .param("comment", "comment"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/restaurant/1"));

        verify(commentService).createComment(user, restaurant, "comment");
    }
}