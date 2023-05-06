package com.app.logic;

import com.app.entity.Customer;
import com.app.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerLogic implements EntityLogic<Customer> {
    @Autowired
    CustomerRepo customerRepo;

    public void saveCustomerLogic(Customer customer) {
        customerRepo.save(customer);
    }

    public void deleteCustomerLogic(long customerId) {
        customerRepo.deleteById(customerId);
    }

    public List<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }

    @Override
    public void saveEntity(Customer customer) {
        customerRepo.save(customer);
    }
}
