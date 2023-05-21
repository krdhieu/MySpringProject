package com.app.entity;

import com.app.entity.generics.BaseEntity;

import javax.persistence.*;

@Entity
public class Account extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String username;
    private String password;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account withUsername(String username) {
        this.username = username;
        return this;
    }

    public Account withPassword(String password) {
        this.password = password;
        return this;
    }

    public Account withCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", customer=" + customer +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
