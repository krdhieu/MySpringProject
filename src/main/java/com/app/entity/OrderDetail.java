package com.app.entity;

import com.app.entity.generics.BaseEntity;

import javax.persistence.*;

@Entity
public class OrderDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private CustomerOrder order;
    @ManyToOne
    private Product product;
    private int quantity;
    private float price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CustomerOrder getOrder() {
        return order;
    }

    public void setOrder(CustomerOrder order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderDetail withOrder(CustomerOrder order) {
        this.order = order;
        return this;
    }

    public OrderDetail withProduct(Product product) {
        this.product = product;
        return this;
    }

    public OrderDetail withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderDetail withPrice(float price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
