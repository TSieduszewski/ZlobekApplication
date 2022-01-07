package com.app.zlobek.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "stuff")
public class BabyStuff {

    @Id
    @Column(name = "parent_id")
    private int id;

    @Column(name = "drywipes")
    private int dryWipes;

    @Column(name = "wetwipes")
    private int wetWipes;

    @Column(name = "diapers")
    private int diapers;

    @Column(name = "towels")
    private int towels;

    @Column(name = "bibs")
    private int bibs;

    @OneToOne
    @MapsId
    @JoinColumn(name = "parent_id")
    private Parent parent;
}
