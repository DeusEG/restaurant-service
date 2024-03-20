package com.deus.restaurantservice.dto;

import com.deus.restaurantservice.model.Restaurant;


public class TableDataDto {
    private Long id;
    private String tableType;
    private Restaurant restaurant;

    public TableDataDto() {
    }

    public TableDataDto(Long id, String tableType, Restaurant restaurant) {
        this.id = id;
        this.tableType = tableType;
        this.restaurant = restaurant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
