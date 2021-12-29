package com.mashcode.fraud.controller;

import com.mashcode.fraud.model.FraudCheckerResponse;
import com.mashcode.fraud.service.FraudCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/v1/fraud-check")
public class FraudController {

    @Autowired
    FraudCheckService fraudCheckService;

    @GetMapping("/{customerId}")
    public FraudCheckerResponse isFraudster(@PathVariable("customerId") String customerId) {

        log.info("api/v1/customer/ ===> received customerId {} :", customerId);
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        return new FraudCheckerResponse(isFraudulentCustomer);
    }

}
