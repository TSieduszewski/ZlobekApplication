package com.app.zlobek.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "babysitters")
public class Babysitter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Pole jest wymagane")
    @NotNull(message = "Pole jest wymagane")
    @Size(min=2, message = "Pole musi zawierać więcej niż dwa znaki")
    @Pattern(regexp = "[a-żA-Ż- ]+", message = "Pole nie może zawierać cyfr")
    private String name;

    @OneToMany(mappedBy = "babysitter", cascade = CascadeType.ALL)
    private List<Shift> shifts;
}
