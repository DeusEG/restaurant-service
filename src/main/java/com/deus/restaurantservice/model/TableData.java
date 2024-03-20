package com.deus.restaurantservice.model;

import javax.persistence.*;

@Entity
@Table(name = "table_data")
public class TableData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_table")
    private Long id;

    @Column(name = "table_type")
    private String tableType;

    @ManyToOne
    @JoinColumn(name = "id_restaurant", referencedColumnName = "id_restaurant")
    private Restaurant restaurant;

    public TableData() {
    }

    public TableData(Long id, String tableType, Restaurant restaurant) {
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
