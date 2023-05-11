package com.app.logic;

import com.app.entity.Customer;
import com.app.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerLogic implements EntityLogic<Customer> {
    @Autowired
    CustomerRepo customerRepo;

    @Override
    public void saveEntity(Customer customer) {
        customerRepo.save(customer);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public void deleteCustomer(long customerId) {
        customerRepo.deleteById(customerId);
    }

    public List<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }

    public List<Customer> findByName(String name) {
        return customerRepo.findByName(name);
    }

    public Customer findById(Long id) {
        Optional<Customer> customerOptional =  customerRepo.findById(id);
        if(customerOptional.isPresent()) {
            return customerOptional.get();
        }
        return null;
    }

    public Customer saveCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepo.findById(customer.getId());
        if(customerOptional.isPresent()) {
            Customer existedCustomer = customerOptional.get();
            existedCustomer
                    .withName(customer.getName())
                    .withAge(customer.getAge())
                    .withGender(customer.getGender())
                    .withAddress(customer.getAddress())
                    .withPhonenumber(customer.getPhonenumber())
                    .withIsAdmin(customer.isAdmin());
            return customerRepo.save(existedCustomer);
        }
        return null;
    }

}
