package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.model.Role;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ModerController.class)
class ModerControllerTest {
    @MockBean
    UserService userService;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CustomDetailsService customDetailsService;


    @Test
    @WithMockUser(roles = "MODER")
    void test_show_moder_panel() throws Exception {
        var role = new Role("MODER");
        var user = new User("name", "telegram",
                role, "password");

        mockMvc.perform(MockMvcRequestBuilders.get("/moder")
                        .sessionAttr("user", user))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show-users"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"));
    }

    @Test
    @WithMockUser(roles = "MODER")
    void test_delete_user() throws Exception {
        var telegram = "telegram";
        mockMvc.perform(MockMvcRequestBuilders.delete("/moder/{telegram}", telegram))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/moder"));
    }

    @Test
    @WithMockUser()
    void test_show_moder_panel_access_denied() throws Exception {
        var role = new Role("USER");
        var user = new User("name", "telegram",
                role, "password");

        mockMvc.perform(MockMvcRequestBuilders.get("/moder")
                        .sessionAttr("user", user))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show-users"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("errorMessage"))
                .andExpect(MockMvcResultMatchers.model().attribute("errorMessage", "Отказано в доступе"));
    }
}
