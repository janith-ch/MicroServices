package com.mashcode.fraud.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@Builder
public class FraudCheckHistory {
    @Id
    private String id;
    private String customerID;
    private Boolean isFraudster;
    private LocalDateTime createdAt;
}
