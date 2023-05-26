package com.app.http;

import com.app.entity.OrderDetails;
import com.app.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-detail")
public class OrderDetailsController {
    @Autowired
    OrderDetailsService orderDetailsService;
    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<OrderDetails> > getAllOrderDetails() {
        return new ResponseEntity<>(orderDetailsService.getAllOrderDetails(), HttpStatus.OK);
    }

    @GetMapping("/find-order-detail-by-id/{id}")
    public @ResponseBody ResponseEntity<OrderDetails> findOrderDetailsById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderDetailsService.findOrderDetailsById(id), HttpStatus.OK);
    }

    @GetMapping("/find-order-by-order-id/{orderId}")
    public @ResponseBody ResponseEntity<List<OrderDetails>> findOrderDetailsByOrderId(@PathVariable("orderId") Long orderId) {
        return new ResponseEntity<>(orderDetailsService.findOrderDetailsByOrderId(orderId), HttpStatus.OK);
    }

    //todo create order detail

    @GetMapping("/find-order-by-customer-id/{customerId}")
    public @ResponseBody ResponseEntity<List<OrderDetails>> findOrderDetailsByCustomerId(@PathVariable("customerId") Long customerId) {
        return new ResponseEntity<>(orderDetailsService.findOrderDetailsByCustomerId(customerId), HttpStatus.OK);
    }

    @PutMapping("/update-order-detail")
    public @ResponseBody ResponseEntity<OrderDetails> updateOrderDetails(@RequestBody OrderDetails orderDetail) {
        return new ResponseEntity<>(orderDetailsService.updateOrderDetails(orderDetail), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public @ResponseBody ResponseEntity<Integer> deleteOrderDetails(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderDetailsService.deleteOrderDetailsById(id), HttpStatus.ACCEPTED);
    }
}
