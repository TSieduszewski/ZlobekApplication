package com.app.zlobek.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
    private String parentFirstName;

    @Column(name = "parentlastname")
    private String parentLastName;

    @Column(name = "childrenfirstname")
    private String childrenFirstName;

    @Column(name = "childrenlastname")
    private String childrenLastName;

    @Column(name = "address")
    private String address;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @OneToMany
    @JoinColumn(name="id_client")
    private List<Message> messages;

    public Parent() {
    }

    public Parent(String parentFirstName, String parentLastName, String address, String phoneNumber, String email) {
        this.parentFirstName = parentFirstName;
        this.parentLastName = parentLastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


}
