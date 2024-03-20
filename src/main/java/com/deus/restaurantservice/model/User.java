package com.deus.restaurantservice.model;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "user_data")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    @Column(name = "name")
    private String name;
    @Column(name = "telegram", unique = true)
    private String telegram;
    @ManyToOne
    @JoinColumn(name = "id_role", referencedColumnName = "id_role")
    private Role role;
    @Column(name = "password", nullable = false)
    private String password;

    public User() {
    }

    public User(Long id_user, String name, String telegram, Role role, String password) {
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

    public void setRole(Role fromRole) {
        this.role = fromRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}