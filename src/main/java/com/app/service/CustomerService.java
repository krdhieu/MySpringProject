package com.app.service;

import com.app.entity.Customer;
import com.app.logic.CustomerLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerLogic customerLogic;

    public List<Customer> getAllCustomer() {
        return customerLogic.getAllCustomer();
    }

    public List<Customer> findByName(String name) {
        return customerLogic.findByName(name);
    }

    public Customer createCustomer(Customer customer) {
        return customerLogic.createCustomer(customer);
    }

    public void deleteCustomer(Long id) {
         customerLogic.deleteCustomer(id);
    }

    public Customer findById(Long id) {
        return customerLogic.findById(id);
    }

    public Customer saveCustomer(Customer customer) {
        return customerLogic.saveCustomer(customer);
    }

}
