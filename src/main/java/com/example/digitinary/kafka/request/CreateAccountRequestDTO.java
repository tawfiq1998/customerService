package com.example.digitinary.kafka.request;

import com.example.digitinary.entity.enums.AccountStatus;
import com.example.digitinary.entity.enums.AccountType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;


@Setter
@Getter
@ToString
public class CreateAccountRequestDTO {
    private String customerUUID;
    private String customerId;
    private AccountType accountType;
    private BigDecimal balance;
    private String createdBy;
    private String modifiedBy;
    private AccountStatus accountStatus;
}
