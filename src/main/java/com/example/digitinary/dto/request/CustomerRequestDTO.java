package com.example.digitinary.dto.request;

import com.example.digitinary.entity.enums.CustomerType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public abstract class CustomerRequestDTO {
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotBlank(message = "legalId is mandatory")
    @Size(min = 7, max = 7, message = "Field must be exactly 7 characters long")
    private String legalId;
    @NotBlank(message = "legalIdType is mandatory")
    private String legalIdType;
    @NotNull(message = "customerType is mandatory")
    private CustomerType customerType;
    private String address;
    private Date dateOfBirth;
    private String createdBy;
    private String modifiedBy;


}
