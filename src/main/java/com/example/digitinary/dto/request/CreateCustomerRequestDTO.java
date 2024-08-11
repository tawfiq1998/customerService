package com.example.digitinary.dto.request;

import com.example.digitinary.service.kafka.request.CreateAccountRequestDTO;
import lombok.*;


@Setter
@Getter
public class CreateCustomerRequestDTO extends CustomerRequestDTO {

    CreateAccountRequestDTO createAccountRequestDTO;
    @Override
    public String toString() {
        return "CreateCustomerRequestDTO{} " + super.toString();
    }
}
