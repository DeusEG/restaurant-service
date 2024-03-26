package com.deus.restaurantservice.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "table_data")
public class TableData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_table")
    private Long id;

    @Column(name = "number_of_seats")
    private Integer numberOfSeats;

    @ManyToOne
    @JoinColumn(name = "id_restaurant", referencedColumnName = "id_restaurant")
    private Restaurant restaurant;

    public TableData() {
    }

    public TableData(Long id, String comment, Integer numberOfSeats, Restaurant restaurant) {
        this.id = id;
        this.restaurant = restaurant;
        this.numberOfSeats = numberOfSeats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableData tableData = (TableData) o;
        return Objects.equals(id, tableData.id) && Objects.equals(numberOfSeats, tableData.numberOfSeats) && Objects.equals(restaurant, tableData.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfSeats, restaurant);
    }

    @Override
    public String toString() {
        return "TableData{" +
                "id=" + id +
                ", numberOfSeats=" + numberOfSeats +
                ", restaurant=" + restaurant +
                '}';
    }
}
