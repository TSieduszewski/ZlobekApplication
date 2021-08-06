package com.app.zlobek.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate date;


    public Message() {

    }

    public Message(int id, String message, LocalDate date) {
        this.id=id;
        this.message = message;
        this.date = date;
    }

    public Message(String message, LocalDate date) {
        this.message = message;
        this.date = date;
    }

    public Message(Parent parent) {
        this.parent = parent;
    }

    public Message(String message, LocalDate date, Parent parent) {
        this.parent = parent;
        this.message = message;
        this.date = date;
    }
}
