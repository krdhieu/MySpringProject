package com.app.logic;

import com.app.entity.Customer;
import com.app.logic.generic.EntityLogic;
import com.app.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerLogic implements EntityLogic<Customer, Long> {
    @Autowired
    CustomerRepo customerRepo;

    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public int deleteCustomer(long customerId) {
        return customerRepo.deleteCustomerById(customerId);
    }

    public List<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }

    public List<Customer> findCustomerByName(String name) {
        return customerRepo.findByName(name);
    }

    public Customer findCustomerById(Long id) {
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

//    public Customer findCustomerByUsernameNPassword(String username, String password) {
//        return customerRepo.findCustomerByUsernameNPassword(username, password);
//    }

    @Override
    public void saveEntity(Customer customer) {
        customerRepo.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        Optional<Customer> customerOptional =  customerRepo.findById(id);
        if(customerOptional.isPresent()) {
            return customerOptional.get();
        }
        return null;
    }

}
