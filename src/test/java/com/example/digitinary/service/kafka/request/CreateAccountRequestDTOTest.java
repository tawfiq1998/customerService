package com.example.digitinary.service.kafka.request;

import com.example.digitinary.entity.enums.AccountStatus;
import com.example.digitinary.entity.enums.AccountType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CreateAccountRequestDTOTest {
    CreateAccountRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        requestDTO = new CreateAccountRequestDTO();
        requestDTO.setAccountType(AccountType.CORPORATE);
        requestDTO.setAccountStatus(AccountStatus.ACTIVE);
        requestDTO.setBalance(new BigDecimal("100.00"));
        requestDTO.setCreatedBy("createdBy");
    }

    @Test
    public void tetCreateAccountRequestDTO() {

        assertNotNull(requestDTO);
        assertEquals(AccountType.CORPORATE, requestDTO.getAccountType());
        assertEquals(AccountStatus.ACTIVE, requestDTO.getAccountStatus());
        assertEquals(new BigDecimal("100.00"), requestDTO.getBalance());
        assertEquals("createdBy", requestDTO.getCreatedBy());
    }
}