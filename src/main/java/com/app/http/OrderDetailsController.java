package com.app.http;

import com.app.config.security.MyUserDetails;
import com.app.entity.CustomerOrder;
import com.app.entity.OrderDetails;
import com.app.service.CustomerOrderService;
import com.app.service.OrderDetailsService;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-detail")
public class OrderDetailsController {
    @Autowired
    OrderDetailsService orderDetailsService;

    @Autowired
    CustomerOrderService customerOrderService;

    @Autowired
    UserDetailsService userDetailsService;

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<OrderDetails>> getAllOrderDetails() {
        return new ResponseEntity<>(orderDetailsService.getAllOrderDetails(), HttpStatus.OK);
    }

    @GetMapping("/find-order-detail-by-id/{id}")
    public @ResponseBody ResponseEntity<OrderDetails> findOrderDetailsById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderDetailsService.findOrderDetailsById(id), HttpStatus.OK);
    }

    @GetMapping("/find-order-by-order-id/{orderId}")
    public @ResponseBody ResponseEntity<List<OrderDetails>> findOrderDetailsByOrderId(
            @PathVariable("orderId") Long orderId,
            Authentication authentication
    ) {
        CustomerOrder customerOrder = customerOrderService.findCustomerOrderById(orderId);
        if (customerOrder == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        MyUserDetails myUserDetails = (MyUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
        if (myUserDetails.isAdmin() || myUserDetails.isCurrentUser(customerOrder.getCustomer().getId()))
            return new ResponseEntity<>(orderDetailsService.findOrderDetailsByOrderId(orderId), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }


    @GetMapping("/find-order-by-customer-id/{customerId}")
    public @ResponseBody ResponseEntity<List<OrderDetails>> findOrderDetailsByCustomerId(
            @PathVariable("customerId") Long customerId,
            Authentication authentication
    ) {
        MyUserDetails myUserDetails = (MyUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
        if (myUserDetails.isAdmin() || myUserDetails.isCurrentUser(customerId))
            return new ResponseEntity<>(orderDetailsService.findOrderDetailsByCustomerId(customerId), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping("/update-order-detail")
    public @ResponseBody ResponseEntity<OrderDetails> updateOrderDetails(
            @RequestBody OrderDetails orderDetail
    ) {
        return new ResponseEntity<>(orderDetailsService.updateOrderDetails(orderDetail), HttpStatus.OK);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public @ResponseBody ResponseEntity<Integer> deleteOrderDetails(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderDetailsService.deleteOrderDetailsById(id), HttpStatus.OK);
    }
}
