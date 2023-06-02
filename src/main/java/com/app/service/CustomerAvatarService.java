package com.app.service;

import com.app.entity.Customer;
import com.app.entity.CustomerAvatar;
import com.app.logic.CustomerAvatarLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CustomerAvatarService {
    @Autowired
    CustomerAvatarLogic customerAvatarLogic;

    public CustomerAvatar findCustomerAvatarById(Long id) {
        return customerAvatarLogic.findCustomerAvatarById(id);
    }

    public CustomerAvatar findCustomerAvatarByCustomer(Customer customer) {
        return customerAvatarLogic.findCustomerAvatarByCustomer(customer);
    }

    public CustomerAvatar addCustomerAvatar(String filePath, Customer customer) {
        return customerAvatarLogic.addCustomerAvatar(filePath, customer);
    }

    public CustomerAvatar updateCustomerAvatar(String filePath, Customer customer) {
        return customerAvatarLogic.updateCustomerAvatar(filePath, customer);
    }

    public int deleteCustomerAvatarById(Long id) throws IOException {
        return customerAvatarLogic.deleteCustomerAvatar(id);
    }
}
