package com.app.logic;

import com.app.entity.OrderDetail;
import com.app.logic.generic.EntityLogic;
import com.app.repository.OrderDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderDetailLogic implements EntityLogic<OrderDetail, Long> {
    @Autowired
    OrderDetailRepo orderDetailRepo;

    public List<OrderDetail> getAllOrderDetail() {
        return orderDetailRepo.findAll();
    }

    public OrderDetail findOrderDetailById(Long id) {
        Optional<OrderDetail> orderDetailOptional = orderDetailRepo.findById(id);
        if (orderDetailOptional.isPresent())
            return orderDetailOptional.get();
        return null;
    }

    public List<OrderDetail> findOrderDetailByOrderId(Long orderId) {
        return orderDetailRepo.findByOrderId(orderId);
    }

    public List<OrderDetail> findOrderDetailByCustomerId(Long customerId) {
        return orderDetailRepo.findByCustomerId(customerId);
    }

    public OrderDetail updateOrderDetail(OrderDetail orderDetail) {
        OrderDetail existedOrderDetail = this.findOrderDetailById(orderDetail.getId());
        if (existedOrderDetail != null) {
            existedOrderDetail
                    .withOrder(orderDetail.getOrder())
                    .withProduct(orderDetail.getProduct())
                    .withQuantity(orderDetail.getQuantity())
                    .withPrice(orderDetail.getPrice());
            orderDetailRepo.save(existedOrderDetail);
            return existedOrderDetail;
        }

        return null;
    }

    @Override
    public void saveEntity(OrderDetail entity) {

    }

    @Override
    public OrderDetail findById(Long id) {
        return null;
    }
}
