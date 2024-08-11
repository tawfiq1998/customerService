package com.example.digitinary.dto.request;

import com.example.digitinary.entity.enums.CustomerStatus;
import com.example.digitinary.entity.enums.CustomerType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class GetCustomersFilterRequestDTO {
    private Date fromDateOfBirth;
    private Date toDateOfBirth;
    private List<String> addresses;
    private String createdBy;
    private String modifiedBy;
    private CustomerType customerType;
    private CustomerStatus customerStatus;

}