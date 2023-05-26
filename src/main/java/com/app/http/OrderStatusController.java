package com.app.http;

import com.app.entity.OrderStatus;
import com.app.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-status")
public class OrderStatusController {
    @Autowired
    OrderStatusService orderStatusService;

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<OrderStatus>> getAllOrderStatus() {
        return new ResponseEntity<>(orderStatusService.getAllOrderStatus(), HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public @ResponseBody ResponseEntity<OrderStatus> findOrderStatusById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderStatusService.findOrderStatusById(id), HttpStatus.OK);
    }

    @GetMapping("/find-by-name/{name}")
    public @ResponseBody ResponseEntity<List<OrderStatus>> findOrderStatusByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(orderStatusService.findOrderStatusByName(name), HttpStatus.OK);
    }

    @PostMapping("/create-order-status")
    public @ResponseBody ResponseEntity<OrderStatus> createOrderStatus(@RequestBody OrderStatus orderStatus) {
        return new ResponseEntity<>(orderStatusService.createOrderStatus(orderStatus), HttpStatus.OK);
    }

    @PutMapping("/update-order-status")
    public @ResponseBody ResponseEntity<OrderStatus> updateOrderStatus(@RequestBody OrderStatus orderStatus) {
        return new ResponseEntity<>(orderStatusService.updateOrderStatus(orderStatus), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public @ResponseBody ResponseEntity<Integer> deleteOrderStatus(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderStatusService.deleteOrderStatusById(id), HttpStatus.ACCEPTED);
    }

}
