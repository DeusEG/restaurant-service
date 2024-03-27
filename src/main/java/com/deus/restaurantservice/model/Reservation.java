package com.deus.restaurantservice.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long id;

    @Column(name = "date")
    private LocalDate date;
    @Column(name = "time")
    private LocalTime time;

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

    public Reservation(User user, TableData table, LocalDate date, LocalTime time, String comment) {
        this.date = date;
        this.time = time;
        this.comment = comment;
        this.user = user;
        this.table = table;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
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
                ", date=" + date +
                ", time=" + time +
                ", comment='" + comment + '\'' +
                ", user=" + user +
                ", table=" + table +
                '}';
    }
}
