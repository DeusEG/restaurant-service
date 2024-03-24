package com.deus.restaurantservice.model;

import javax.persistence.*;

@Entity
@Table(name = "table_data")
public class TableData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_table")
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "number_of_seats")
    private Integer numberOfSeats;

    @ManyToOne
    @JoinColumn(name = "id_restaurant", referencedColumnName = "id_restaurant")
    private Restaurant restaurant;

    public TableData() {
    }

    public TableData(Long id, String comment, Integer numberOfSeats, Restaurant restaurant) {
        this.id = id;
        this.comment = comment;
        this.restaurant = restaurant;
        this.numberOfSeats = numberOfSeats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
