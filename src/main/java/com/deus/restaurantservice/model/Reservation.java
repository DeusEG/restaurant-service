package com.deus.restaurantservice.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "comment")
    private String comment;

    @ManyToOne()
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;
    @ManyToOne()
    @JoinColumn(name = "id_table", referencedColumnName = "id_table")
    private TableData table;

    public Reservation() {
    }

    public Reservation(User user, TableData table, LocalDateTime dateTime, String comment) {
        this.dateTime = dateTime;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", comment='" + comment + '\'' +
                ", user=" + user +
                ", table=" + table +
                '}';
    }
}
