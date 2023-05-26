package com.app.entity;

import com.app.entity.generics.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class CustomerOrder extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orderDetailsList;

    private float totalPrice;
    private LocalDateTime orderAt;
    private LocalDateTime shippedAt;
    private LocalDateTime completeAt;
    private LocalDateTime cancelledAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDetails> getOrderDetailList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getOrderAt() {
        return orderAt;
    }

    public void setOrderAt(LocalDateTime orderDate) {
        this.orderAt = orderDate;
    }

    public LocalDateTime getShippedAt() {
        return shippedAt;
    }

    public void setShippedAt(LocalDateTime shippedAt) {
        this.shippedAt = shippedAt;
    }

    public LocalDateTime getCompleteAt() {
        return completeAt;
    }

    public void setCompleteAt(LocalDateTime completeAt) {
        this.completeAt = completeAt;
    }

    public LocalDateTime getCancelledAt() {
        return cancelledAt;
    }

    public void setCancelledAt(LocalDateTime cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    public CustomerOrder withStatus(OrderStatus orderStatus) {
        this.status = orderStatus;
        return this;
    }

    public CustomerOrder withCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public CustomerOrder withTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public CustomerOrder withOrderAt(LocalDateTime date) {
        this.orderAt = date;
        return this;
    }

    public CustomerOrder withShippedAt(LocalDateTime date) {
        this.shippedAt = date;
        return this;
    }

    public CustomerOrder withCompletedAt(LocalDateTime date) {
        this.completeAt = date;
        return this;
    }

    public CustomerOrder withCancelledAt(LocalDateTime date) {
        this.cancelledAt = date;
        return this;
    }


    @Override
    public String toString() {
        return "CustomerOrder{" +
                "id=" + id +
                ", status=" + status +
                ", customer=" + customer +
                ", orderDetailList=" + orderDetailsList +
                ", totalPrice=" + totalPrice +
                ", orderAt=" + orderAt +
                ", shippedAt=" + shippedAt +
                ", completeAt=" + completeAt +
                ", cancelledAt=" + cancelledAt +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
