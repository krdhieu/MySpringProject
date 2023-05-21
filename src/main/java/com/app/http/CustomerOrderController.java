package com.app.http;

import com.app.entity.Customer;
import com.app.entity.CustomerOrder;
import com.app.entity.OrderStatus;
import com.app.service.CustomerOrderService;
import org.apache.coyote.Response;
import org.aspectj.weaver.patterns.OrTypePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/customer-order")
public class CustomerOrderController {
    @Autowired
    CustomerOrderService customerOrderService;

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<CustomerOrder>> getAllCustomerOrder() {
        return new ResponseEntity<>(customerOrderService.getAllOrder(), HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public @ResponseBody ResponseEntity<CustomerOrder> findCustomerOrderById(@PathVariable("id") Long customerOrderId) {
        return new ResponseEntity<>(customerOrderService.findCustomerOrderById(customerOrderId), HttpStatus.OK);
    }

    @GetMapping("/find-by-order-date/{date}")
    public @ResponseBody ResponseEntity<List<CustomerOrder>> findCustomerByCreatedDate(@RequestParam("date")  @DateTimeFormat(pattern = "dd.MM.yyyy") Date date) {
        return new ResponseEntity<>(customerOrderService.findCustomerOrderByCreatedDate(date), HttpStatus.OK);
    }

    @GetMapping("/find-by-customer-id/{customerId}")
    public @ResponseBody ResponseEntity<List<CustomerOrder>> findCustomerOrderByCustomerId(@PathVariable("customerId") Long customerId) {
        return new ResponseEntity<>(customerOrderService.findCustomerOrderByCustomerId(customerId), HttpStatus.OK);
    }

    @GetMapping("/find-by-status-id/{statusId}")
    public @ResponseBody ResponseEntity<List<CustomerOrder>> findCustomerOrderByOrderStatus(@PathVariable("statusId") Long statusId){
        return new ResponseEntity<>(customerOrderService.findCustomerOrderByStatusId(statusId), HttpStatus.OK);
    }

    @PostMapping("/find-by-customer-and-created-date")
    public @ResponseBody ResponseEntity<List<CustomerOrder>> findCustomerOrderByCustomerAndCreatedDate(@RequestBody Map<String, Object> requestBody) {
        Customer customer = (Customer) requestBody.get("customer");
        Date createdDate = new Date((long) requestBody.get("createdDate"));
        return new ResponseEntity<>(customerOrderService.findCustomerOrderByCustomerAndCreatedDate(customer, createdDate), HttpStatus.OK);
    }


    @PostMapping("/create-customer-order")
    public @ResponseBody ResponseEntity<CustomerOrder> createCustomerOrder(@RequestBody CustomerOrder customerOrder) {
        return new ResponseEntity<>(customerOrderService.createCustomerOrder(customerOrder), HttpStatus.OK);
    }

    @PutMapping("/update-customer-order")
    public @ResponseBody ResponseEntity<CustomerOrder> updateCustomerOrder(@RequestBody CustomerOrder customerOrder) {
        return new ResponseEntity<>(customerOrderService.updateCustomerOrder(customerOrder), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public @ResponseBody ResponseEntity<Integer> deleteCustomerOrderById(@PathVariable("id") Long customerOrderId) {
        return new ResponseEntity<>(customerOrderService.deleteCustomerOrderById(customerOrderId), HttpStatus.ACCEPTED);
    }
}
