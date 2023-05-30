package com.app.logic;

import com.app.entity.CustomerOrder;
import com.app.entity.OrderDetails;
import com.app.entity.Product;
import com.app.entity.dto.Cart;
import com.app.logic.common.EntityLogic;
import com.app.repository.OrderDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderDetailsLogic implements EntityLogic<OrderDetails, Long> {
    @Autowired
    OrderDetailsRepo orderDetailsRepo;

    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsRepo.findAll();
    }

    public OrderDetails findOrderDetailsById(Long id) {
        Optional<OrderDetails> orderDetailsOptional = orderDetailsRepo.findById(id);
        if (orderDetailsOptional.isPresent())
            return orderDetailsOptional.get();
        return null;
    }

    public List<OrderDetails> findOrderDetailsByOrderId(Long orderId) {
        return orderDetailsRepo.findByOrderId(orderId);
    }

    public List<OrderDetails> findOrderDetailsByCustomerId(Long customerId) {
        return orderDetailsRepo.findByCustomerId(customerId);
    }

    public OrderDetails createOrderDetail(CustomerOrder customerOrder, Product product, Integer quantity, Float price) {
        OrderDetails orderDetails
                = new OrderDetails()
                .withOrder(customerOrder)
                .withProduct(product)
                .withQuantity(quantity)
                .withPrice(price);
        return orderDetailsRepo.save(orderDetails);
    }


    public OrderDetails updateOrderDetails(OrderDetails orderDetails) {
        OrderDetails existedOrderDetails = this.findOrderDetailsById(orderDetails.getId());
        if (existedOrderDetails != null) {
            existedOrderDetails
                    .withOrder(orderDetails.getOrder())
                    .withProduct(orderDetails.getProduct())
                    .withQuantity(orderDetails.getQuantity())
                    .withPrice(orderDetails.getPrice());
            orderDetailsRepo.save(existedOrderDetails);
            return existedOrderDetails;
        }

        return null;
    }


    public int deleteOrderDetailById(Long orderDetailsId) {
        return orderDetailsRepo.deleteOrderDetailsById(orderDetailsId);
    }

    @Override
    public void saveEntity(OrderDetails entity) {
        orderDetailsRepo.save(entity);
    }

    @Override
    public OrderDetails findById(Long id) {
        Optional<OrderDetails> orderDetailsOptional = orderDetailsRepo.findById(id);
        if (orderDetailsOptional.isPresent())
            return orderDetailsOptional.get();
        return null;
    }
}
