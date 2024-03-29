package com.deus.restaurantservice.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long id;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "comment")
    private String comment;

    @Column(name = "number_of_seats", nullable = false)
    private Integer numberOfSeats;

    @ManyToOne()
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @ManyToOne()
    @JoinColumn(name = "id_table", referencedColumnName = "id_table")
    private TableData table;

    public Reservation() {
    }

    public Reservation(User user, TableData table, LocalDateTime dateTime, String comment, Integer numberOfSeats) {
        this.dateTime = dateTime;
        this.comment = comment;
        this.numberOfSeats = numberOfSeats;
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

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id) && Objects.equals(dateTime, that.dateTime) && Objects.equals(comment, that.comment) && Objects.equals(numberOfSeats, that.numberOfSeats) && Objects.equals(user, that.user) && Objects.equals(table, that.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, comment, numberOfSeats, user, table);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", comment='" + comment + '\'' +
                ", numberOfSeats='" + numberOfSeats + '\'' +
                ", user=" + user +
                ", table=" + table +
                '}';
    }
}
