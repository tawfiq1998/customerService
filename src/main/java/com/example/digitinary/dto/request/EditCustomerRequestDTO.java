package com.example.digitinary.dto.request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class EditCustomerRequestDTO extends CustomerRequestDTO {
    private Long id;

    @Override
    public String toString() {
        return "EditCustomerRequestDTO{" +
                "id=" + id +
                "} " + super.toString();
    }
}
