package com.app.logic;

import com.app.entity.Customer;
import com.app.entity.CustomerAvatar;
import com.app.entity.CustomerOrder;
import com.app.repository.CustomerAvatarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;

@Component
public class CustomerAvatarLogic {
    @Autowired
    CustomerAvatarRepo customerAvatarRepo;

    @Autowired
    @Qualifier("appUrl")
    String appUrl;


    public CustomerAvatar findCustomerAvatarById(Long id) {
        if (id == null) return null;
        Optional<CustomerAvatar> customerAvatarOptional = customerAvatarRepo.findById(id);
        if (customerAvatarOptional.isPresent()) {
            return customerAvatarOptional.get();
        }
        return null;
    }

    public CustomerAvatar findCustomerAvatarByCustomer(Customer customer) {
        if (customer == null) return null;
        return customerAvatarRepo.findByCustomer(customer);
    }


    public CustomerAvatar addCustomerAvatar(String filePath, Customer customer) {
        CustomerAvatar newAvatar = new CustomerAvatar();
        newAvatar.setImgPath(appUrl + filePath);
        newAvatar.setCustomer(customer);
        return customerAvatarRepo.save(newAvatar);
    }


    public CustomerAvatar updateCustomerAvatar(String filePath, Customer customer) {
        CustomerAvatar currentCustomerAvatar = customerAvatarRepo.findByCustomer(customer);
        if (currentCustomerAvatar != null) {
            currentCustomerAvatar.setImgPath(filePath);
            return customerAvatarRepo.save(currentCustomerAvatar);
        }
        return null;
    }

    public int deleteCustomerAvatar(Long id) throws IOException {
        CustomerAvatar currentAvatar = this.findCustomerAvatarById(id);
        if (currentAvatar == null) return 0;
        int rowEffected = customerAvatarRepo.deleteCustomerAvatarById(id);
        if (rowEffected == 1) {
            String filePath = "src/main/resources/" + extractPathFromImgPath(currentAvatar.getImgPath());
            Path path = Paths.get(filePath);
            Files.delete(path);
        }
        return rowEffected;
    }

    public String extractPathFromImgPath(String imgPath) {
        String[] imgPathElements = imgPath.split("/");
        int startIndex = -1;
        for (int i = 0; i < imgPathElements.length; i++) {
            if (imgPathElements[i].equals("static")) {
                startIndex = i;
                break;
            }
        }
        if (startIndex != -1) {
            return String.join("/", Arrays.copyOfRange(imgPathElements, startIndex, imgPathElements.length));
        }
        return null;
    }
}
