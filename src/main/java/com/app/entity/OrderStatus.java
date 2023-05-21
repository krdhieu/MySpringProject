package com.app.entity;

import com.app.entity.generics.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class OrderStatus extends BaseEntity {
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

    public OrderStatus withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orderList=" + orderList +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
