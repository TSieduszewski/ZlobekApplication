package com.app.zlobek.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="tuition")
    private int tuition;

    @Column(name="meals")
    private int meals;

    @Column(name="month")
    private LocalDate month;

    @Column(name="summary")
    private int summary;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_client")
    private Parent parent;

    public Payment(int tuition, int meals, LocalDate month, int summary, Parent parent) {
        this.tuition = tuition;
        this.meals = meals;
        this.month = month;
        this.summary = summary;
        this.parent = parent;
    }

    public Payment() {
    }
}
