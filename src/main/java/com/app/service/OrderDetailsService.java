package com.app.service;

import com.app.entity.OrderDetails;
import com.app.logic.OrderDetailsLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {
    @Autowired
    OrderDetailsLogic orderDetailsLogic;

    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsLogic.getAllOrderDetails();
    }

    public OrderDetails findOrderDetailsById(Long id) {
        return orderDetailsLogic.findOrderDetailsById(id);
    }

    public List<OrderDetails> findOrderDetailsByOrderId(Long orderId) {
        return orderDetailsLogic.findOrderDetailsByOrderId(orderId);
    }

    public List<OrderDetails> findOrderDetailsByCustomerId(Long customerId) {
        return orderDetailsLogic.findOrderDetailsByCustomerId(customerId);
    }

    public OrderDetails updateOrderDetails(OrderDetails orderDetails) {
        return orderDetailsLogic.updateOrderDetails(orderDetails);
    }

    public int deleteOrderDetailsById(Long id) {
        return orderDetailsLogic.deleteOrderDetailById(id);
    }
}
