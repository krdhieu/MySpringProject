package com.app.http;

import com.app.entity.Customer;
import com.app.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@Api(value = "myproject", tags = {"customer"})
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @ApiOperation(value = "get all customer info", response = Customer.class)
    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<Customer>> getCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomer(), HttpStatus.OK);
    }

    @ApiOperation(value = "create customer", response = Customer.class)
    @PostMapping("/create-customer")
    public @ResponseBody ResponseEntity<Customer> createCustomer(@RequestBody Customer newCustomer) {
        return new ResponseEntity<>(customerService.createCustomer(newCustomer), HttpStatus.OK);
    }

    @ApiOperation(value = "delete customer by customer's id", response = Customer.class)
    @DeleteMapping("/delete-customer/{id}")
    public @ResponseBody ResponseEntity<Integer> deleteCustomer(@PathVariable("id") Long customerId) {
        return new ResponseEntity<>(customerService.deleteCustomer(customerId), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "find customer by customer's id, return customer info", response = Customer.class)
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable("id") Long customerId) {
        return new ResponseEntity<Customer>(customerService.findCustomerById(customerId), HttpStatus.OK);
    }

    @ApiOperation(value = "update customer is existed in db", response = Customer.class)
    @PutMapping("/update-customer")
    public @ResponseBody ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) throws IOException {
        return new ResponseEntity<>(customerService.saveCustomer(customer), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "find customer by name, return a list of customer match with name at path variable", response = Customer.class)
    @PostMapping("/find-by-name/{name}")
    public @ResponseBody ResponseEntity<List<Customer>> findCustomerByName(@PathVariable("name") String customerName) {
        return new ResponseEntity<>(customerService.findCustomerByName(customerName), HttpStatus.OK);
    }


}
