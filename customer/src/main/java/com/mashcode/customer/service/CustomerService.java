package com.mashcode.customer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.mashcode.customer.exception.ErrorResponse;
import com.mashcode.customer.exception.UserNotFoundException;
import com.mashcode.customer.model.Customer;
import com.mashcode.customer.model.RequestCustomer;
import com.mashcode.customer.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RestTemplate restTemplate;

    public void insertCustomer(RequestCustomer requestCustomer) {

        Customer customer = Customer.builder().
                firstName(requestCustomer.getFirstName()).
                lastName(requestCustomer.getLastName()).
                email(requestCustomer.getEmail()).build();

        if(customer.getFirstName().getClass().getSimpleName().equals("String")){
            throw new UserNotFoundException("invalid first name : "+customer.getFirstName());

        }

        customerRepository.save(customer);
        log.info("save customer {} :", customer);

//        JsonNode jsonNode = restTemplate.getForObject(
//                "http://localhost:8071/api/v1/fraud-check/{customerId}",
//                JsonNode.class,
//                customer.getId()
//        );
//
//        log.info("Response of check fraud user {} :", jsonNode);
//
//        if (jsonNode.get("isFraudster").asBoolean()) {
//            throw new IllegalStateException("Fraudster User !!!!!");
//        }

    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        log.info("customers List {} ", customers);
        return customers;
    }


}
