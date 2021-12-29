package com.mashcode.fraud.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.mashcode.fraud.model.FraudCheckerResponse;
import com.mashcode.fraud.service.FraudCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@RequestMapping("api/v1/fraud-check")
public class FraudController {

    @Autowired
    FraudCheckService fraudCheckService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/{customerId}")
    public FraudCheckerResponse isFraudster(@PathVariable("customerId") String customerId) {

        log.info("api/v1/customer/ ===> received customerId {} :", customerId);
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        return new FraudCheckerResponse(isFraudulentCustomer);
    }

    @PostMapping("/")
    public void customerRegistration(@RequestBody JsonNode jsonNode){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JsonNode> request = new HttpEntity<>(jsonNode, headers);
        try {
            ResponseEntity<JsonNode> responseJson =   restTemplate.exchange(
                    "http://localhost:8070/api/v1/customer/",
                    HttpMethod.POST,
                    request,
                    JsonNode.class
            );

            log.info("api/v1/customer/ ===> Response {} :", responseJson);
        }catch (Exception e){
            throw new RuntimeException("================>"+e.getMessage());
        }


    }

}
