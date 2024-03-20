package com.deus.restaurantservice.dto;

import com.deus.restaurantservice.model.TableData;
import com.deus.restaurantservice.model.User;


import java.time.LocalDateTime;

public class ReservationDto {
    private Long id;
    private LocalDateTime dateTime;
    private User user;
    private TableData table;

    public ReservationDto() {
    }

    public ReservationDto(Long id, LocalDateTime dateTime, User user, TableData table) {
        this.id = id;
        this.dateTime = dateTime;
        this.user = user;
        this.table = table;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TableData getTable() {
        return table;
    }

    public void setTable(TableData table) {
        this.table = table;
    }
}
