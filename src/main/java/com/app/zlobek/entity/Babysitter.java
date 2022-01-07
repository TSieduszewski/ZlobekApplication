package com.app.zlobek.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "babysitters")
public class Babysitter {

    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_shift")
    private LocalDateTime startShift;

    @Column(name = "end_shift")
    private LocalDateTime endShift;
}
