package com.app.zlobek.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
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
    public static String childrenFirstName;

    @Column(name = "childrenlastname")
    public static String childrenLastName;

    @Column(name = "address")
    private String address;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "email")
    private String email;


    public Parent() {
    }

    public Parent(int id, String parentFirstName, String parentLastName, String address, String phoneNumber, String email) {
        this.id = id;
        this.parentFirstName = parentFirstName;
        this.parentLastName = parentLastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Parent(String parentFirstName, String parentLastName, String address, String phoneNumber, String email) {
        this.parentFirstName = parentFirstName;
        this.parentLastName = parentLastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParentFirstName() {
        return parentFirstName;
    }

    public void setParentFirstName(String parentFirstName) {
        this.parentFirstName = parentFirstName;
    }

    public String getParentLastName() {
        return parentLastName;
    }

    public void setParentLastName(String parentLastName) {
        this.parentLastName = parentLastName;
    }

    public static String getChildrenFirstName() {
        return childrenFirstName;
    }

    public static void setChildrenFirstName(String childrenFirstName) {
        Parent.childrenFirstName = childrenFirstName;
    }

    public static String getChildrenLastName() {
        return childrenLastName;
    }

    public static void setChildrenLastName(String childrenLastName) {
        Parent.childrenLastName = childrenLastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", parentFirstName='" + parentFirstName + '\'' +
                ", parentLastName='" + parentLastName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
