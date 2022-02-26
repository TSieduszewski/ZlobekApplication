package com.app.zlobek.entity;

import lombok.Data;

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

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Parent parent;

    @Column(name = "attendancedate")
    private LocalDate attendanceDate;

    @Column(name = "attendant")
    Boolean attendant;

    @Column(name = "verification")
    Boolean verification;

    public Attendance() {
    }

    public Attendance(Parent parent, LocalDate attendanceDate, Boolean attendant) {
        this.parent = parent;
        this.attendanceDate = attendanceDate;
        this.attendant = attendant;
    }

    public Attendance(Parent parent, LocalDate attendanceDate, Boolean attendant, Boolean verification) {
        this.parent = parent;
        this.attendanceDate = attendanceDate;
        this.attendant = attendant;
        this.verification = verification;
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
    }

    public Attendance(Boolean verification) {
        this.verification = verification;
    }


}
