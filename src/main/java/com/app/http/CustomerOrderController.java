package com.app.http;

import com.app.config.security.MyUserDetails;
import com.app.entity.Customer;
import com.app.entity.CustomerOrder;
import com.app.entity.dto.Cart;
import com.app.entity.dto.CartService;
import com.app.logic.CustomerLogic;
import com.app.service.CustomerOrderService;
import net.bytebuddy.utility.nullability.AlwaysNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/customer-order")
public class CustomerOrderController {
    @Autowired
    CustomerOrderService customerOrderService;

    @Autowired
    CartService cartService;

    @Autowired
    UserDetailsService userDetailsService;


    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<CustomerOrder>> getAllCustomerOrder() {
        return new ResponseEntity<>(customerOrderService.getAllOrder(), HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public @ResponseBody ResponseEntity<CustomerOrder> findCustomerOrderById(
            @PathVariable("id") Long customerOrderId,
            Authentication authentication
    ) {
        MyUserDetails myUserDetails = (MyUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
        CustomerOrder customerOrder = customerOrderService.findCustomerOrderById(customerOrderId);
        if (myUserDetails.isAdmin() || myUserDetails.isCurrentUser(customerOrder.getCustomer().getId()))
            return new ResponseEntity<>(customerOrder, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/find-by-order-date/{date}")
    public @ResponseBody ResponseEntity<List<CustomerOrder>> findCustomerByCreatedDate(@RequestParam("date") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date) {
        return new ResponseEntity<>(customerOrderService.findCustomerOrderByCreatedDate(date), HttpStatus.OK);
    }

    @GetMapping("/find-by-customer-id-or-and-date/{customerId}/{date}")
    public @ResponseBody ResponseEntity<List<CustomerOrder>> findCustomerOrderByCustomerId(
            @PathVariable("customerId") Long customerId,
            @RequestParam("date") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date,
            Authentication authentication
    ) {
        MyUserDetails myUserDetails = (MyUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
        if (myUserDetails.isAdmin() || myUserDetails.isCurrentUser(customerId))
            return new ResponseEntity<>(customerOrderService.findCustomerOrderByCustomerIdAndOrDate(customerId, date), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/find-by-status-id/{statusId}")
    public @ResponseBody ResponseEntity<List<CustomerOrder>> findCustomerOrderByOrderStatus(@PathVariable("statusId") Long statusId) {
        return new ResponseEntity<>(customerOrderService.findCustomerOrderByStatusId(statusId), HttpStatus.OK);
    }

    @PostMapping("/find-by-customer-and-created-date")
    public @ResponseBody ResponseEntity<List<CustomerOrder>> findCustomerOrderByCustomerAndCreatedDate(
            @RequestBody Map<String, Object> requestBody,
            Authentication authentication
    ) {
        MyUserDetails myUserDetails = (MyUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
        Customer customer = (Customer) requestBody.get("customer");
        Date createdDate = new Date((long) requestBody.get("createdDate"));
        if (myUserDetails.isAdmin() || myUserDetails.isCurrentUser(customer.getId()))
            return new ResponseEntity<>(customerOrderService.findCustomerOrderByCustomerAndCreatedDate(customer, createdDate), HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/create-customer-order")
    public @ResponseBody ResponseEntity<CustomerOrder> createCustomerOrder(
            HttpServletRequest request,
            @RequestBody CustomerOrder customerOrder,
            Authentication authentication
    ) {
        MyUserDetails myUserDetails = (MyUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
        if (myUserDetails.isCurrentUser(customerOrder.getCustomer().getId())) {
            Cookie cookies[] = request.getCookies();
            if (cookies == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Cart currentCart = cartService.decodeCartFromCookie(cookies);
            if (currentCart == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(customerOrderService.createCustomerOrder(customerOrder, currentCart), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    //update order status
    @PutMapping("/update-customer-order")
    public @ResponseBody ResponseEntity<CustomerOrder> updateCustomerOrder(@RequestBody CustomerOrder customerOrder) {
        return new ResponseEntity<>(customerOrderService.updateCustomerOrder(customerOrder), HttpStatus.OK);
    }
    //todo
    @DeleteMapping("/delete-by-id/{id}")
    public @ResponseBody ResponseEntity<Integer> deleteCustomerOrderById(
            @PathVariable("id") Long customerOrderId,
            Authentication authentication
    ) {
        CustomerOrder customerOrder = customerOrderService.findCustomerOrderById(customerOrderId);
        if(customerOrder==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        MyUserDetails myUserDetails = (MyUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
        if(myUserDetails.isAdmin() || myUserDetails.isCurrentUser(customerOrder.getCustomer().getId()))
            return new ResponseEntity<>(customerOrderService.deleteCustomerOrderById(customerOrderId), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
