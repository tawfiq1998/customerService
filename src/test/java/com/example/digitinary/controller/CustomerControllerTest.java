package com.example.digitinary.controller;

import com.example.digitinary.dto.request.CreateCustomerRequestDTO;
import com.example.digitinary.dto.request.EditCustomerRequestDTO;
import com.example.digitinary.dto.request.GetCustomersFilterRequestDTO;
import com.example.digitinary.entity.enums.CustomerStatus;
import com.example.digitinary.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    private CustomerController customerController;

    @BeforeEach
    public void setUp() {
        customerController = new CustomerController(customerService);
    }

    @Test
    public void testCreateCustomer() {
        CreateCustomerRequestDTO requestDTO = new CreateCustomerRequestDTO();

        customerController.createCustomer(requestDTO);
        verify(customerService, times(1)).createCustomer(any(CreateCustomerRequestDTO.class));
    }

    @Test
    public void testEditCustomer() {
        EditCustomerRequestDTO requestDTO = new EditCustomerRequestDTO();

        customerController.editCustomer(requestDTO);

        verify(customerService, times(1)).editCustomer(any(EditCustomerRequestDTO.class));
    }

    @Test
    public void testChangeCustomerStatus() {
        CustomerStatus status = CustomerStatus.ACTIVE;

        customerController.changeCustomerStatus(1L, status);

        verify(customerService, times(1)).changeCustomerStatus(anyLong(), any(CustomerStatus.class));
    }

    @Test
    public void testGetCustomersWithFiltration() {

        GetCustomersFilterRequestDTO requestDTO = new GetCustomersFilterRequestDTO();
        requestDTO.setAddresses(List.of("address1", "address2", "address3", "address4"));
        requestDTO.setCreatedBy("createdBy");
        requestDTO.setFromDateOfBirth(new Date());
        customerController.getCustomersWithFiltration(1, 10, Optional.of(requestDTO));

        verify(customerService, times(1)).getFilteredCustomerList(anyInt(), anyInt(), any());

    }

    @Test
    public void testGetCustomers() {

        customerController.getCustomers();

        verify(customerService, times(1)).getCustomers();

    }
}