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
    private User admin;

    public Restaurant() {
    }

    public Restaurant(Long id, String address, User admin) {
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

    public void setAdmin(User user) {
        this.admin = user;
    }
}
