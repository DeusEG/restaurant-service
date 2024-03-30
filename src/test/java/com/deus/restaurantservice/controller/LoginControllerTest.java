package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.security.CustomDetailsService;
import com.deus.restaurantservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
class LoginControllerTest {
    @MockBean
    private UserService userService;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CustomDetailsService customDetailsService;

    @Test
    void test_get_login() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("/login"));
    }
}
