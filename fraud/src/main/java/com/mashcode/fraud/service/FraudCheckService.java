package com.mashcode.fraud.service;


import com.mashcode.fraud.model.FraudCheckHistory;
import com.mashcode.fraud.repository.FraudCheckHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@Slf4j

public class FraudCheckService {

    @Autowired
    private FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentCustomer(String customerId){

        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerID(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;

    }

}
