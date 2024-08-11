package com.example.digitinary.dto.response;

import com.example.digitinary.entity.enums.CustomerStatus;
import com.example.digitinary.entity.enums.CustomerType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class GetCustomerFilterResponseDTO {


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
