package com.app.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @OneToMany(mappedBy = "status")
    private List<CustomerOrder> orderList;

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

    @Override
    public String toString() {
        return "OderStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
