package com.deus.restaurantservice.dto;

import com.deus.restaurantservice.model.Role;

public class UserDto {
    private Long id_user;
    private String name;
    private String telegram;
    private Role role;
    private String password;

    public UserDto() {
    }

    public UserDto(Long id_user, String name, String telegram, Role role, String password) {
        this.id_user = id_user;
        this.name = name;
        this.telegram = telegram;
        this.role = role;
        this.password = password;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
