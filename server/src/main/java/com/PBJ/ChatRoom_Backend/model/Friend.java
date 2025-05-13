package com.PBJ.ChatRoom_Backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

@Entity
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user1_id", nullable = false)
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user2_id", nullable = false)
    private User user2;

    @NotNull
    @Column(name = "created_at", updatable = false, nullable = false)
    private java.time.LocalDateTime created_at;

    public Friend() {
        this.created_at = java.time.LocalDateTime.now();
    }

    public Friend(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.created_at = java.time.LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public java.time.LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(java.time.LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
