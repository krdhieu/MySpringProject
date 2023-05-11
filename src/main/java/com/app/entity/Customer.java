package com.app.entity;

import com.sun.istack.NotNull;
import org.hibernate.annotations.NotFound;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String name;
    @NotNull
    private int age;
    @NotNull
    private boolean gender;
    @NotNull
    private String address;

    @NotNull
    private String phonenumber;

    @NotNull
    @Column(columnDefinition = "boolean default false")
    private boolean isAdmin;


    public Customer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


    public Customer withName(String name) {
        this.name = name;
        return this;
    }

    public Customer withAge(int age) {
        this.age = age;
        return this;
    }

    public Customer withGender(boolean gender) {
        this.gender = gender;
        return this;
    }

    public Customer withAddress(String address) {
        this.address = address;
        return this;
    }

    public Customer withPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
        return this;
    }

    public Customer withIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
