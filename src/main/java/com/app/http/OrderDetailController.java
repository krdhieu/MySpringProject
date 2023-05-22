package com.app.http;

import com.app.entity.OrderDetail;
import com.app.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-detail")
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;
    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<OrderDetail> > getAllOrderDetail() {
        return new ResponseEntity<>(orderDetailService.getAllOrderDetail(), HttpStatus.OK);
    }

    @GetMapping("/find-order-detail-by-id/{id}")
    public @ResponseBody ResponseEntity<OrderDetail> findOrderDetailById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderDetailService.findOrderDetailById(id), HttpStatus.OK);
    }

    @GetMapping("/find-order-by-order-id/{orderId}")
    public @ResponseBody ResponseEntity<List<OrderDetail>> findOrderDetailByOrderId(@PathVariable("orderId") Long orderId) {
        return new ResponseEntity<>(orderDetailService.findOrderDetailByOrderId(orderId), HttpStatus.OK);
    }

    @GetMapping("/find-order-by-customer-id/{customerId}")
    public @ResponseBody ResponseEntity<List<OrderDetail>> findOrderDetailByCustomerId(@PathVariable("customerId") Long customerId) {
        return new ResponseEntity<>(orderDetailService.findOrderDetailByCustomerId(customerId), HttpStatus.OK);
    }

    @PutMapping("/update-order-detail")
    public @ResponseBody ResponseEntity<OrderDetail> updateOrderDetail(@RequestBody OrderDetail orderDetail) {
        return new ResponseEntity<>(orderDetailService.updateOrderDetail(orderDetail), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public @ResponseBody ResponseEntity<Integer> deleteOrderDetail(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderDetailService.deleteOrderDetailById(id), HttpStatus.ACCEPTED);
    }
}
