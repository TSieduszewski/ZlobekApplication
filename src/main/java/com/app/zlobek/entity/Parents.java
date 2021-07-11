package com.app.zlobek.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class Parents {

    @Id
    private int id;

    private String parentFirstName;
    private String parentLastName;
    public static String childrenFirstName;
    public static String childrenLastName;
    private String address;
    private String phoneNumber;
    private String email;

}
