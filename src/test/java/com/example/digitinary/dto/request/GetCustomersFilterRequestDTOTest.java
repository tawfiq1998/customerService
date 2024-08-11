package com.example.digitinary.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetCustomersFilterRequestDTOTest {

    GetCustomersFilterRequestDTO requestDTO;
    Date date = new Date();

    @BeforeEach
    void setUp() {
        requestDTO = new GetCustomersFilterRequestDTO();
        requestDTO.setAddresses(List.of("address1", "address2", "address3", "address4"));
        requestDTO.setCreatedBy("createdBy");
        requestDTO.setFromDateOfBirth(date);
    }

    @Test
    public void testGetCustomersFilterRequestDTOTest() {

        assertEquals(requestDTO.getAddresses(), List.of("address1", "address2", "address3", "address4"));
        assertEquals(requestDTO.getCreatedBy(), "createdBy");
        assertEquals(requestDTO.getFromDateOfBirth(),date);

    }
}