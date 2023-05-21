package com.app.service;

import com.app.entity.Customer;
import com.app.entity.CustomerOrder;
import com.app.entity.OrderStatus;
import com.app.logic.CustomerOrderLogic;
import com.app.repository.CustomerOrderRepo;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerOrderService {
    @Autowired
    CustomerOrderLogic customerOrderLogic;

    public List<CustomerOrder> getAllOrder() {
        return customerOrderLogic.getAllOrder();
    }

    public CustomerOrder findCustomerOrderById(Long id) {
        return customerOrderLogic.findCustomerOrderById(id);
    }

    public List<CustomerOrder> findCustomerOrderByCreatedDate(Date date) {
        return customerOrderLogic.findCustomerOrderByCreatedDate(date);
    }

    public List<CustomerOrder> findCustomerOrderByCustomerId(Long id) {
        return customerOrderLogic.findCustomerOrderByCustomerId(id);
    }

    public List<CustomerOrder> findCustomerOrderByCustomerAndCreatedDate(Customer customer, Date dateCreated) {
        return customerOrderLogic.findCustomerOrderByCustomerAndCreatedDate(customer, dateCreated);
    }

    public List<CustomerOrder> findCustomerOrderByStatusId(Long statusId) {
        return customerOrderLogic.findCustomerOrderByStatusId(statusId);
    }

    public CustomerOrder createCustomerOrder(CustomerOrder customerOrder) {
        return customerOrderLogic.createCustomerOrder(customerOrder);
    }

    public CustomerOrder updateCustomerOrder(CustomerOrder customerOrder) {
        return customerOrderLogic.updateCustomerOrder(customerOrder);
    }

    public int deleteCustomerOrderById(Long id) {
        return customerOrderLogic.deleteCustomerOrderById(id);
    }


}
