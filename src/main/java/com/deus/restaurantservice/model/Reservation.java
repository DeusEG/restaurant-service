package com.deus.restaurantservice.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long id;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;
    @OneToOne
    @JoinColumn(name = "id_table", referencedColumnName = "id_table")
    private TableData table;

    public Reservation() {
    }

    public Reservation(Long id, LocalDateTime dateTime, User user, TableData table) {
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
