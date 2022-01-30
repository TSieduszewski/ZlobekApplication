package com.app.zlobek.entity;

import com.app.zlobek.util.validation.DateRange;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "shifts")

public class Shift implements Comparable<Shift> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "start_shift")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startShift;

    @Column(name = "end_shift")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endShift;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_babysitter")
    private Babysitter babysitter;


    @Override
    public int compareTo(Shift o) {
        return this.getStartShift().compareTo(o.getStartShift());
    }
}
