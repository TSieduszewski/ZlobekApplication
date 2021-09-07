package com.app.zlobek.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "messages_to_director")
public class Question{

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


    public Question() {

    }

    public Question(int id, String message, LocalDateTime date) {
        this.id=id;
        this.message = message;
        this.date = date;
    }

    public Question(String message, LocalDateTime date) {
        this.message = message;
        this.date = date;
    }

    public Question(Parent parent) {
        this.parent = parent;
    }

    public Question(Parent parent, LocalDateTime date) {
        this.parent = parent;
        this.date = date;
    }

    public Question(String message, LocalDateTime date, Parent parent) {
        this.parent = parent;
        this.message = message;
        this.date = date;
    }

}
