package com.deus.restaurantservice.controller;

import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.security.CustomDetailsService;
import com.deus.restaurantservice.service.ReservationService;
import com.deus.restaurantservice.service.RestaurantService;
import com.deus.restaurantservice.service.TableDataService;
import com.deus.restaurantservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReservationController.class)
class ReservationControllerTest {
    @MockBean
    UserService userService;
    @MockBean
    RestaurantService restaurantService;
    @MockBean
    ReservationService reservationService;
    @MockBean
    TableDataService tableDataService;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CustomDetailsService customDetailsService;

    @Test
    @WithMockUser(roles = "USER")
    void test_get_all_reservation() throws Exception {
        var user = new User();

        when(reservationService.getAllReservationByUser(user)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/reservation/show").sessionAttr("user", user))
                .andExpect(status().isOk())
                .andExpect(view().name("show-user-reservation"))
                .andExpect(model().attributeExists("reservations"));

        verify(reservationService, times(1)).getAllReservationByUser(user);
    }

    @Test
    @WithMockUser(roles = "USER")
    void test_show_form_new_reservation() throws Exception {
        var restaurantId = 1L;
        var restaurant = new Restaurant();

        when(restaurantService.getRestaurantById(restaurantId)).thenReturn(restaurant);
        when(tableDataService.getAllTableByRestaurant(restaurant)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/reservation/{restaurantId}", restaurantId))
                .andExpect(status().isOk())
                .andExpect(view().name("new-reservation"))
                .andExpect(model().attributeExists("tables"));

        verify(restaurantService, times(1)).getRestaurantById(restaurantId);
        verify(tableDataService, times(1)).getAllTableByRestaurant(restaurant);
    }

    @Test
    @WithMockUser(roles = "USER")
    void test_show_create_reservation() throws Exception {
        var date = "2024-04-01";
        var time = "18:00";
        var comment = "Test reservation";
        var tableId = 1L;
        var numberOfSeats = 4;
        var user = new User();

        mockMvc.perform(post("/reservation/{restaurantId}", 1L)
                        .param("date", date)
                        .param("time", time)
                        .param("comment", comment)
                        .param("table", String.valueOf(tableId))
                        .param("numberOfSeats", String.valueOf(numberOfSeats))
                        .sessionAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/reservation/show"));

        verify(reservationService, times(1)).createReservation(user,
                tableId, date, time, comment, numberOfSeats);
    }
}