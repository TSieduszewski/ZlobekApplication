package com.app.zlobek.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name="clients")
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String parentFirstName;
    private String parentLastName;
    public static String childrenFirstName;
    public static String childrenLastName;
    private String address;
    private String phoneNumber;
    private String email;

}
