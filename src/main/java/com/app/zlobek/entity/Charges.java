package com.app.zlobek.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "charges")
@Data
public class Charges {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "charge")
    private int charge;

    @Column(name = "description")
    private String description;

    public Charges(int charge, String description) {
        this.charge = charge;
        this.description = description;
    }

    public Charges() {
    }
}
