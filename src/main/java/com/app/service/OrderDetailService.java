package com.app.service;

import com.app.entity.OrderDetail;
import com.app.logic.OrderDetailLogic;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    OrderDetailLogic orderDetailLogic;

    public List<OrderDetail> getAllOrderDetail() {
        return orderDetailLogic.getAllOrderDetail();
    }

    public OrderDetail findOrderDetailById(Long id) {
        return orderDetailLogic.findOrderDetailById(id);
    }

    public List<OrderDetail> findOrderDetailByOrderId(Long orderId) {
        return orderDetailLogic.findOrderDetailByOrderId(orderId);
    }

    public List<OrderDetail> findOrderDetailByCustomerId(Long customerId) {
        return orderDetailLogic.findOrderDetailByCustomerId(customerId);
    }

    public OrderDetail updateOrderDetail(OrderDetail orderDetail) {
        return orderDetailLogic.updateOrderDetail(orderDetail);
    }

    public int deleteOrderDetailById(Long id) {
        return orderDetailLogic.deleteOrderDetailById(id);
    }
}
