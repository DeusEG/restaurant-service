package com.deus.restaurantservice.model;

import javax.persistence.*;


@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_restaurant")
    private Long id;
    @Column(name = "address", nullable = false, unique = true)
    private String address;
    @OneToOne
    @JoinColumn(name = "id_admin", referencedColumnName="id_user")
    private User user;

    public Restaurant() {
    }

    public Restaurant(Long id, String address, User user) {
        this.id = id;
        this.address = address;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
