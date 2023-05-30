package com.app.logic;

import com.app.entity.*;
import com.app.entity.dto.Cart;
import com.app.logic.common.EntityLogic;
import com.app.repository.CustomerOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CustomerOrderLogic implements EntityLogic<CustomerOrder, Long> {
    @Autowired
    CustomerOrderRepo customerOrderRepo;

    @Autowired
    OrderStatusLogic orderStatusLogic;

    @Autowired
    OrderDetailsLogic orderDetailsLogic;

    @Autowired
    ProductLogic productLogic;

    public List<CustomerOrder> getAllOrder() {
        return customerOrderRepo.findAll();
    }

    public CustomerOrder findCustomerOrderById(Long id) {
        Optional<CustomerOrder> customerOrderOptional = customerOrderRepo.findById(id);
        if (customerOrderOptional.isPresent()) {
            return customerOrderOptional.get();
        }
        return null;
    }

    public List<CustomerOrder> findCustomerOrderByCustomerIdAndOrDate(Long customerId, Date orderedDate) {
        return customerOrderRepo.findCustomerOrderByCustomerIdAndOrDate(customerId, orderedDate);
    }

    public List<CustomerOrder> findCustomerOrderByCreatedDate(Date date) {
        return customerOrderRepo.findCustomerOrderByCreatedDate(date);
    }

    public List<CustomerOrder> findCustomerOrderByCustomerAndCreatedDate(Customer customer, Date dateCreated) {
        List<CustomerOrder> customerOrdersFindByCreatedDate = this.findCustomerOrderByCreatedDate(dateCreated);
        List<CustomerOrder> customerOrdersFindByCreatedDateAndCustomer
                = customerOrdersFindByCreatedDate
                .stream()
                .filter(order -> order.getCustomer().getId() == customer.getId())
                .collect(Collectors.toList());
        return customerOrdersFindByCreatedDateAndCustomer;
    }

    public List<CustomerOrder> findCustomerOrderByStatusId(Long statusId) {
        return customerOrderRepo.findCustomerOrderByOrderStatus(statusId);
    }

    public CustomerOrder createCustomerOrder(CustomerOrder customerOrder, Cart cart) {
        if (cart == null || cart.getCartItems().size() == 0)
            return null;
        List<OrderStatus> orderStatuses = orderStatusLogic.findOrderStatusByName("ORDERED");
        CustomerOrder order = customerOrder.withStatus(orderStatuses.get(0));
        HashMap<Long, Integer> products = cart.getCartItems();
        float totalPrice = 0;
        for (Map.Entry<Long, Integer> item : products.entrySet()) {
            Product product = productLogic.findProductById(item.getKey());
            int quantity = item.getValue();
            float price = quantity * product.getPrice();
            orderDetailsLogic.createOrderDetail(customerOrder, product, quantity, price);
            totalPrice += price;
        }
        customerOrder.withTotalPrice(totalPrice);
        return customerOrderRepo.save(customerOrder);
    }

    public CustomerOrder updateCustomerOrder(CustomerOrder customerOrder) {
        CustomerOrder existedCustomerOrder = this.findCustomerOrderById(customerOrder.getId());
        if (existedCustomerOrder != null) {
            existedCustomerOrder
                    .withStatus(customerOrder.getStatus());
            switch (customerOrder.getStatus().getName()) {
                case "COMPLETED":
                    existedCustomerOrder.withCompletedAt(LocalDateTime.now());
                    break;
                case "SHIPPED":
                    existedCustomerOrder.withShippedAt(LocalDateTime.now());
                    break;
                case "CANCELLED":
                    existedCustomerOrder.withCancelledAt(LocalDateTime.now());
                    break;
                default:
                    break;
            }
            customerOrderRepo.save(existedCustomerOrder);
            return existedCustomerOrder;
        }
        return null;
    }

    public int deleteCustomerOrderById(Long id) {
        return customerOrderRepo.deleteCustomerOrderById(id);
    }

    @Override
    public void saveEntity(CustomerOrder entity) {
        customerOrderRepo.save(entity);
    }

    @Override
    public CustomerOrder findById(Long id) {
        Optional<CustomerOrder> customerOrderOptional = customerOrderRepo.findById(id);
        if (customerOrderOptional.isPresent())
            return customerOrderOptional.get();
        return null;
    }

}
