package com.app.zlobek.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_client")
    private Parent parent;

    @Column(name = "message")
    String message;

    @Column(name = "date")
    LocalDateTime date;


    public Message() {

    }

    public Message(int id, String message, LocalDateTime date) {
        this.id = id;
        this.message = message;
        this.date = date;
    }

    public Message(String message, LocalDateTime date) {
        this.message = message;
        this.date = date;
    }

    public Message(Parent parent) {
        this.parent = parent;
    }

    public Message(String message, LocalDateTime date, Parent parent) {
        this.parent = parent;
        this.message = message;
        this.date = date;
    }
}
