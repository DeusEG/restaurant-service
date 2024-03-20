package com.deus.restaurantservice.dto;

import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.model.User;

public class CommentDto {
    private Long id;
    private User user;
    private Restaurant restaurant;
    private String comment;

    public CommentDto() {
    }

    public CommentDto(Long id, User user, Restaurant restaurant, String comment) {
        this.id = id;
        this.user = user;
        this.restaurant = restaurant;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
