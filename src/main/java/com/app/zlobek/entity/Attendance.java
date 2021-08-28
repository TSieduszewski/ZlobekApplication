package com.app.zlobek.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //@ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Parent parent;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "attendancedate")
    private LocalDate attendanceDate;

    @Column(name = "attendant")
    Boolean attendant;

    public Attendance() {
    }

    public Attendance(Parent parent, LocalDate attendanceDate, Boolean attendant) {
        this.parent = parent;
        this.attendanceDate = attendanceDate;
        this.attendant = attendant;
    }

    public Attendance(LocalDate attendanceDate, Boolean attendant) {
        this.attendanceDate = attendanceDate;
        this.attendant = attendant;
    }

    public Attendance(Parent parent, LocalDate attendanceDate) {
        this.parent = parent;
        this.attendanceDate = attendanceDate;
    }

    public Attendance(int id, Parent parent, LocalDate attendanceDate, Boolean attendant) {
        this.id = id;
        this.parent = parent;
        this.attendanceDate = attendanceDate;
        this.attendant = attendant;
        System.out.println("wszystko konstruktor attendance");
    }

}
