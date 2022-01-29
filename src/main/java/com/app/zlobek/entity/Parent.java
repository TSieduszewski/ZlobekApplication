package com.app.zlobek.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name="clients")
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "parentfirstname")
    @NotEmpty(message = "Pole jest wymagane")
    @NotNull(message = "Pole jest wymagane")
    @Size(min=2, message = "Pole musi zawierać więcej niż dwa znaki")
    private String parentFirstName;

    @Column(name = "parentlastname")
    @NotEmpty(message = "Pole jest wymagane")
    @NotNull(message = "Pole jest wymagane")
    @Size(min=2, message = "Pole musi zawierać więcej niż dwa znaki")
    private String parentLastName;

    @Column(name = "childrenfirstname")
    @NotEmpty(message = "Pole jest wymagane")
    @NotNull(message = "Pole jest wymagane")
    @Size(min=2, message = "Pole musi zawierać więcej niż dwa znaki")
    private String childrenFirstName;

    @Column(name = "childrenlastname")
    @NotEmpty(message = "Pole jest wymagane")
    @NotNull(message = "Pole jest wymagane")
    @Size(min=2, message = "Pole musi zawierać więcej niż dwa znaki")
    private String childrenLastName;

    @Column(name = "address")
    @NotEmpty(message = "Pole jest wymagane")
    @NotNull(message = "Pole jest wymagane")
    @Size(min=2, message = "Pole musi zawierać więcej niż dwa znaki")
    private String address;

    @Column(name = "phonenumber")
    @NotEmpty(message = "Pole jest wymagane")
    @NotNull(message = "Pole jest wymagane")
    @Size(min=2, message = "Pole musi zawierać więcej niż dwa znaki")
    @Digits(message = "Telefon musi być liczbą", integer = 9, fraction = 9)
    @Size(min = 6, max = 9, message = "Niepoprawny format numeru telefonu. W przypadku telefonów komórkowych należy użyć formatu: 000000000, w przypadku telefonu stacjonarnego należy użyć formatu: 000000000. Nie stosujemy spacji, myślników, kropek itd.")
    private String phoneNumber;

    @Column(name = "email")
    @NotEmpty(message = "Pole jest wymagane")
    @NotNull(message = "Pole jest wymagane")
    @Size(min=2, message = "Pole musi zawierać więcej niż dwa znaki")
    @Email(message = "Niepoprawny format email")
    private String email;

    @OneToOne(mappedBy = "parent", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private BabyStuff babyStuff;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Message> messages;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Attendance> attendances;

    @OneToMany(mappedBy = "verification", cascade = CascadeType.ALL)
    private List<Attendance> verification;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Payment> payments;

    public Parent() {
    }

    public Parent(String parentFirstName, String parentLastName, String address, String phoneNumber, String email) {
        this.parentFirstName = parentFirstName;
        this.parentLastName = parentLastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Parent(int id, String parentFirstName, String parentLastName, String childrenFirstName, String childrenLastName, String address, String phoneNumber, String email) {
        this.id = id;
        this.parentFirstName = parentFirstName;
        this.parentLastName = parentLastName;
        this.childrenFirstName = childrenFirstName;
        this.childrenLastName = childrenLastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Parent(int id){
        this.id=id;
    }
}
