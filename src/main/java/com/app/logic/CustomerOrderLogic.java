package com.app.logic;

import com.app.entity.Customer;
import com.app.entity.CustomerOrder;
import com.app.entity.OrderStatus;
import com.app.logic.common.EntityLogic;
import com.app.repository.CustomerOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomerOrderLogic implements EntityLogic<CustomerOrder, Long> {
    @Autowired
    CustomerOrderRepo customerOrderRepo;

    @Autowired
    OrderStatusLogic orderStatusLogic;

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

    public List<CustomerOrder> findCustomerOrderByCustomerId(Long customerId) {
        return customerOrderRepo.findCustomerOrderByCustomerId(customerId);
    }

    public List<CustomerOrder> findCustomerOrderByCreatedDate(Date date) {
        return customerOrderRepo.findCustomerOrderByCreatedDate(date);
    }

    public CustomerOrder createCustomerOrder(CustomerOrder customerOrder) {
        List<OrderStatus> orderStatuses = orderStatusLogic.findOrderStatusByName("ORDERED");
        customerOrder.withStatus(orderStatuses.get(0));
        return customerOrderRepo.save(customerOrder);
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

    public CustomerOrder updateCustomerOrder(CustomerOrder customerOrder) {
        CustomerOrder existedCustomerOrder = this.findCustomerOrderById(customerOrder.getId());
        if (existedCustomerOrder != null) {
            existedCustomerOrder
                    .withCustomer(customerOrder.getCustomer())
                    .withStatus(customerOrder.getStatus())
                    .withTotalPrice(customerOrder.getTotalPrice());
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
