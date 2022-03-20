package com.app.zlobek.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@Table(name = "stuff")
public class BabyStuff {

    @Id
    @Column(name = "parent_id")
    private int id;

    @Column(name = "drywipes")
    @PositiveOrZero(message = "Pole musi być liczbą dodatnią lub zerem")
    @NotNull(message = "Pole nie może być puste")
    private Integer dryWipes;

    @Column(name = "wetwipes")
    @PositiveOrZero(message = "Pole musi być liczbą lub zerem")
    @NotNull(message = "Pole nie może być puste")
    private Integer wetWipes;

    @Column(name = "diapers")
    @PositiveOrZero(message = "Pole musi być liczbą lub zerem")
    @NotNull(message = "Pole nie może być puste")
    private Integer diapers;

    @Column(name = "towels")
    @PositiveOrZero(message = "Pole musi być liczbą lub zerem")
    @NotNull(message = "Pole nie może być puste")
    private Integer towels;

    @Column(name = "bibs")
    @PositiveOrZero(message = "Pole musi być liczbą lub zerem")
    @NotNull(message = "Pole nie może być puste")
    private Integer bibs;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "parent_id")
    private Parent parent;

    public BabyStuff(Integer dryWipes, Integer wetWipes, Integer diapers, Integer towels, Integer bibs, Parent parent) {
        this.dryWipes = dryWipes;
        this.wetWipes = wetWipes;
        this.diapers = diapers;
        this.towels = towels;
        this.bibs = bibs;
        this.parent = parent;
    }

    public BabyStuff() {

    }
}
