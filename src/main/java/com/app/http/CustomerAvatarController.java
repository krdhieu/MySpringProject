package com.app.http;

import com.app.config.security.MyUserDetails;
import com.app.entity.Customer;
import com.app.entity.CustomerAvatar;
import com.app.logic.CustomerAvatarLogic;
import com.app.logic.CustomerLogic;
import com.app.service.CustomerAvatarService;
import com.app.service.CustomerService;
import com.app.upload.FileUploadLogic;
import com.app.upload.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1/customer-avatar")
public class CustomerAvatarController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerAvatarController.class);
    @Autowired
    CustomerAvatarService customerAvatarService;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    CustomerService customerService;
    @Autowired
    @Qualifier("avatarDir")
    String avatarDir;

    @GetMapping("/find-by-id/{id}")
    public @ResponseBody ResponseEntity<CustomerAvatar> findCustomerAvatarById(@PathVariable("id") Long avatarId) {
        if (avatarId == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(customerAvatarService.findCustomerAvatarById(avatarId), HttpStatus.OK);
    }

    @GetMapping("/find-current-customer-avatar")
    public @ResponseBody ResponseEntity<CustomerAvatar> findCurrentCustomerAvatar(Authentication authentication) {
        MyUserDetails myUserDetails = (MyUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
        return new ResponseEntity<>(customerAvatarService.findCustomerAvatarByCustomer(myUserDetails.getCustomer()), HttpStatus.OK);
    }

    @GetMapping("/find-by-customer-id/{customerId}")
    public @ResponseBody ResponseEntity<CustomerAvatar> findCustomerAvatarByCustomer(@PathVariable("customerId") Long customerId) {
        if (customerId == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Customer customer = customerService.findCustomerById(customerId);
        if (customer == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(customerAvatarService.findCustomerAvatarByCustomer(customer), HttpStatus.OK);
    }

    @PostMapping("/upload-avatar/{customerId}")
    public @ResponseBody ResponseEntity<CustomerAvatar> uploadAvatar(
            @PathVariable("customerId") Long customerId,
            @RequestParam("imageFile") MultipartFile file,
            Authentication authentication
    ) {
        String fileType = file.getContentType();
        long fileSize = file.getSize();
        if (!fileType.equals("image/png") && !fileType.equals("image/jpeg") && !fileType.equals("image/jpg") && fileSize <= 5 * 1024 * 1024) {
            return new ResponseEntity("Invalid file type. Only PNG, JPEG, and JPG files are allowed, file size have to <= 5MB", HttpStatus.BAD_REQUEST);
        }
        Customer customer = customerService.findCustomerById(customerId);
        if (customer == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (file == null || file.isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        MyUserDetails myUserDetails = (MyUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
        if (myUserDetails.isAdmin() || myUserDetails.isCurrentUser(customerId)) {
            String uploadDir = avatarDir;
            String filePath = fileUploadService.uploadFile(file, uploadDir);
            logger.warn("===============>file path to save: " + filePath);
            if (customerAvatarService.findCustomerAvatarByCustomer(customer) == null)
                return new ResponseEntity(customerAvatarService.addCustomerAvatar(filePath, customer), HttpStatus.OK);
            return new ResponseEntity(customerAvatarService.updateCustomerAvatar(filePath, customer), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<Integer> deleteAvatarById(
            @PathVariable("id") Long avatarId,
            Authentication authentication
    ) {
        CustomerAvatar customerAvatar = customerAvatarService.findCustomerAvatarById(avatarId);
        if (customerAvatar == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        MyUserDetails myUserDetails = (MyUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
        if (myUserDetails.isAdmin() || myUserDetails.isCurrentUser(customerAvatar.getCustomer().getId())) {
            try {
                return new ResponseEntity<>(customerAvatarService.deleteCustomerAvatarById(avatarId), HttpStatus.OK);
            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
