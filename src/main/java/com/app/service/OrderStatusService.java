package com.app.service;

import com.app.entity.OrderStatus;
import com.app.logic.OrderStatusLogic;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderStatusService {
    @Autowired
    OrderStatusLogic orderStatusLogic;

    public List<OrderStatus> getAllOrderStatus() {
        return orderStatusLogic.getAllOrderStatus();
    }

    public List<OrderStatus> findOrderStatusByName(String name) {
        return orderStatusLogic.findOrderStatusByName(name);
    }

    public OrderStatus findOrderStatusById(Long id) {
        return orderStatusLogic.findOrderStatusById(id);
    }

    public OrderStatus createOrderStatus(OrderStatus orderStatus) {
        return orderStatusLogic.createOrderStatus(orderStatus);
    }

    public int deleteOrderStatusById(Long id) {
        return orderStatusLogic.deleteOrderStatusById(id);
    }

    public OrderStatus updateOrderStatus(OrderStatus orderStatus) {
        return orderStatusLogic.updateOrderStatus(orderStatus);
    }
}
