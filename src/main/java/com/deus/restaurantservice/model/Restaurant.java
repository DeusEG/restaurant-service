package com.deus.restaurantservice.model;

import javax.persistence.*;
import javax.persistence.Table;


@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "address", nullable = false, unique = true)
    private String address;
    @OneToOne
    @JoinColumn(name = "id_admin", referencedColumnName="id_user")
    private User fromUser;

    public Restaurant() {
    }

    public Restaurant(Long id, String address, User fromUser) {
        this.id = id;
        this.address = address;
        this.fromUser = fromUser;
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

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }
}
