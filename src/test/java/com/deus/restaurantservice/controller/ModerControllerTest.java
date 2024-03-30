package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.security.CustomDetailsService;
import com.deus.restaurantservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(ModerController.class)
class ModerControllerTest {
    @MockBean
    UserServiceImpl userServiceImpl;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CustomDetailsService customDetailsService;


    @Test
    @WithMockUser(roles = "MODER")
    void showModerPanel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/moder"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show-users"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"));
    }

    @Test
    @WithMockUser(roles = "MODER")
    void deleteUser() throws Exception {
        String telegram = "vvv";
        mockMvc.perform(MockMvcRequestBuilders.delete("/moder/{telegram}", telegram))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/moder"));
    }
}