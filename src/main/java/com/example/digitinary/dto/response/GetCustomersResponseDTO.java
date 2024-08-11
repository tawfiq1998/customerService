package com.example.digitinary.dto.response;

import com.example.digitinary.entity.enums.CustomerStatus;
import com.example.digitinary.entity.enums.CustomerType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GetCustomersResponseDTO {
    private String name;
    private String legalId;
    private String legalIdType;
    private String address;
    private Date dateOfBirth;
    private String createdBy;
    private String modifiedBy;
    private Date creationDate;
    private CustomerType customerType;
    private CustomerStatus customerStatus;
}
