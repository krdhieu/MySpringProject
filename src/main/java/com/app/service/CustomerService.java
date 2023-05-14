package com.app.service;

import com.app.entity.Customer;
import com.app.logic.CustomerLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerLogic customerLogic;

    public List<Customer> getAllCustomer() {
        return customerLogic.getAllCustomer();
    }

    public List<Customer> findCustomerByName(String name) {
        return customerLogic.findCustomerByName(name);
    }

    public Customer createCustomer(Customer customer) {
        return customerLogic.createCustomer(customer);
    }

    @Transactional
    public int deleteCustomer(Long id) {
         return customerLogic.deleteCustomer(id);
    }

    public Customer findCustomerById(Long id) {
        return customerLogic.findCustomerById(id);
    }

    public Customer saveCustomer(Customer customer) {
        return customerLogic.saveCustomer(customer);
    }

}
