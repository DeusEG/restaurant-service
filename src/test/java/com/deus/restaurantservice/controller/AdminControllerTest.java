package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.model.Role;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.security.CustomDetailsService;
import com.deus.restaurantservice.service.CommentService;
import com.deus.restaurantservice.service.RestaurantService;
import com.deus.restaurantservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(AdminController.class)
class AdminControllerTest {
    @MockBean
    UserService userService;
    @MockBean
    RestaurantService restaurantService;
    @MockBean
    CommentService commentService;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CustomDetailsService customDetailsService;

    @Test
    @WithMockUser(roles = "ADMIN")
    void test_show_admin_panel() throws Exception {
        var role = new Role("ADMIN");
        var user = new User("name", "telegram",
                role, "password");

        mockMvc.perform(MockMvcRequestBuilders.get("/admin")
                        .sessionAttr("user", user))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin-restaurant-comments"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("comments"));
    }

    @Test
    @WithMockUser()
    void test_show_admin_panel_access_denied() throws Exception {
        var role = new Role("USER");
        var user = new User("name", "telegram",
                role, "password");

        mockMvc.perform(MockMvcRequestBuilders.get("/admin")
                        .sessionAttr("user", user))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin-restaurant-comments"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("errorMessage"))
                .andExpect(MockMvcResultMatchers.model().attribute("errorMessage", "Отказано в доступе"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void test_delete_comment() throws Exception {
        var commentId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/admin/{commentId}", commentId))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin"));
    }
}