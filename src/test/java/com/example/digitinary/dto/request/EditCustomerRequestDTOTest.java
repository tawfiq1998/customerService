package com.example.digitinary.dto.request;

import com.example.digitinary.entity.enums.CustomerType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditCustomerRequestDTOTest {

    @Test
    public void testEditCustomerRequestDTO() {
        // Given
        EditCustomerRequestDTO dto = new EditCustomerRequestDTO();
        dto.setId(123L);

        // When
        String result = dto.toString();

        // Then
        String expected = "EditCustomerRequestDTO{id=123} " +
                "CustomerRequestDTO(name=null, legalId=null, legalIdType=null, customerType=null, address=null, dateOfBirth=null, createdBy=null, modifiedBy=null)";
        assertEquals(expected, result);
    }

}