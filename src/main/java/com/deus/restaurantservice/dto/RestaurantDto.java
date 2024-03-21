package com.deus.restaurantservice.dto;

import com.deus.restaurantservice.model.User;

public class RestaurantDto {
    private Long id;
    private String address;
    private User admin;

    public RestaurantDto() {
    }

    public RestaurantDto(Long id, String address, User admin) {
        this.id = id;
        this.address = address;
        this.admin = admin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }
}
