package com.deus.restaurantservice.model;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "user_data")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long userId;
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

    public User(Long userId, String name, String telegram, Role role, String password) {
        this.userId = userId;
        this.name = name;
        this.telegram = telegram;
        this.role = role;
        this.password = password;
    }
    public User(String name, String telegram, Role role, String password) {
        this.name = name;
        this.telegram = telegram;
        this.role = role;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id_user) {
        this.userId = id_user;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(name, user.name) && Objects.equals(telegram, user.telegram) && Objects.equals(role, user.role) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, telegram, role, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + userId +
                ", name='" + name + '\'' +
                ", telegram='" + telegram + '\'' +
                ", role=" + role +
                ", password='" + password + '\'' +
                '}';
    }
}