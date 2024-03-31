package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.security.CustomDetailsService;
import com.deus.restaurantservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    UserService userService;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CustomDetailsService customDetailsService;

    @Test
    @WithMockUser(roles = "USER")
    void test_show_user_info() throws Exception {
        var user = new User();

        mockMvc.perform(MockMvcRequestBuilders.get("/user/info").sessionAttr("user", user))
                .andExpect(status().isOk())
                .andExpect(view().name("user-info"))
                .andExpect(model().attribute("user", user));
    }

    @Test
    @WithMockUser(roles = "USER")
    void test_show_form_change_info() throws Exception {
        var user = new User();

        mockMvc.perform(MockMvcRequestBuilders.get("/user/info/change")
                        .sessionAttr("user", user))
                .andExpect(status().isOk())
                .andExpect(view().name("change-user-info"))
                .andExpect(model().attribute("user", user));
    }
}