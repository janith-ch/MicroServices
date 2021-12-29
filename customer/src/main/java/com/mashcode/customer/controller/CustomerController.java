package com.mashcode.customer.controller;

import com.mashcode.customer.exception.ErrorResponse;
import com.mashcode.customer.exception.UserNotFoundException;
import com.mashcode.customer.model.Customer;
import com.mashcode.customer.model.RequestCustomer;
import com.mashcode.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/")
    public void insertCustomer(@RequestBody RequestCustomer requestCustomer){

        log.info("api/v1/customer/ ===> Customer registration received json {} :",requestCustomer);
         customerService.insertCustomer(requestCustomer);
    }
    @GetMapping("/")
    public List<Customer> getAllCustomers(){

       return customerService.getAllCustomers();
    }


}
