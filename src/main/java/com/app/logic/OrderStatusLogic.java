package com.app.logic;

import com.app.entity.OrderStatus;
import com.app.logic.generic.EntityLogic;
import com.app.repository.OrderStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class OrderStatusLogic implements EntityLogic<OrderStatus, Long> {
    @Autowired
    OrderStatusRepo orderStatusRepo;

    public List<OrderStatus> getAllOrderStatus() {
        return orderStatusRepo.findAll();
    }

    public List<OrderStatus> findOrderStatusByName(String name) {
        return orderStatusRepo.findOrderStatusByName(name);
    }

    public OrderStatus findOrderStatusById(Long id) {
        Optional<OrderStatus> orderStatusOptional = orderStatusRepo.findById(id);
        if (orderStatusOptional.isPresent())
            return orderStatusOptional.get();
        return null;
    }

    public OrderStatus createOrderStatus(OrderStatus orderStatus) {
        return orderStatusRepo.save(orderStatus);
    }

    public OrderStatus updateOrderStatus(OrderStatus orderStatus) {
        OrderStatus existedOrderStatus = this.findOrderStatusById(orderStatus.getId());
        if (existedOrderStatus != null) {
            existedOrderStatus.withName(orderStatus.getName());
            orderStatusRepo.save(existedOrderStatus);
            return existedOrderStatus;
        }
        return null;
    }

    public int deleteOrderStatusById(Long id) {
        return orderStatusRepo.deleteOrderStatusById(id);
    }

    @Override
    public void saveEntity(OrderStatus entity) {
        orderStatusRepo.save(entity);
    }

    @Override
    public OrderStatus findById(Long id) {
        Optional<OrderStatus> orderStatusOptional = orderStatusRepo.findById(id);
        if (orderStatusOptional.isPresent())
            return orderStatusOptional.get();
        return null;
    }
}
