package com.example.digitinary.dto.request;


import com.example.digitinary.entity.enums.CustomerType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class CreateCustomerRequestDTOTest {

    private CreateCustomerRequestDTO dto;

    @BeforeEach
    public void setUp() {
        dto = new CreateCustomerRequestDTO();
        dto.setName("Jane Doe");
        dto.setLegalId("987654321");
        dto.setLegalIdType("PASSPORT");
        dto.setCustomerType(CustomerType.INVESTMENT); // Assuming CustomerType is an enum
        dto.setAddress("456 Elm St");
        dto.setDateOfBirth(new Date());
        dto.setCreatedBy("admin");
        dto.setModifiedBy("admin");
    }

    @Test
    public void testToString() {
        // When
        String result = dto.toString();

        // Then
        String expected = "CreateCustomerRequestDTO{} " +
                "CustomerRequestDTO(name=Jane Doe, legalId=987654321, legalIdType=PASSPORT, customerType=INVESTMENT, address=456 Elm St, dateOfBirth=" + dto.getDateOfBirth() + ", createdBy=admin, modifiedBy=admin)";
        assertEquals(expected, result);
    }

}