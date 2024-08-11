package com.example.digitinary.service.impl;

import com.example.digitinary.dto.request.CreateCustomerRequestDTO;
import com.example.digitinary.dto.request.EditCustomerRequestDTO;
import com.example.digitinary.entity.Customer;
import com.example.digitinary.entity.enums.CustomerStatus;
import com.example.digitinary.entity.enums.CustomerType;
import com.example.digitinary.exception.CustomerNotFoundException;
import com.example.digitinary.repository.CustomerRepository;
import com.example.digitinary.service.mapper.CustomerMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceImplTest {
    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;
    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private CustomerRepository customerRepository; // Mock the repository

    @Test
    @Order(1)
    void createCustomer() {
        CreateCustomerRequestDTO requestDTO = mock(CreateCustomerRequestDTO.class);

        Customer customer = mock(Customer.class);

        given(customerRepository.save(customer)).willReturn(customer);

        when(customerMapper.createCustomerDTOToCustomer(requestDTO)).thenReturn(customer);
        customerServiceImpl.createCustomer(requestDTO);

        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void editCustomer() {
        EditCustomerRequestDTO requestDTO = mock(EditCustomerRequestDTO.class);

        Customer customer = mock(Customer.class);

        given(customerRepository.save(customer)).willReturn(customer);
        given(customerRepository.findById(anyLong())).willReturn(Optional.of(customer));

        when(customerMapper.editCustomerDTOToCustomer(requestDTO)).thenReturn(customer);
        customerServiceImpl.editCustomer(requestDTO);

        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void editCustomer_whenCustomerNotFound() {
        EditCustomerRequestDTO requestDTO = mock(EditCustomerRequestDTO.class);
        when(requestDTO.getId()).thenReturn(1L);

        given(customerRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerServiceImpl.editCustomer(requestDTO));

    }

    @Test
    void changeCustomerStatus() {


        Customer customer = mock(Customer.class);

        given(customerRepository.save(customer)).willReturn(customer);
        given(customerRepository.findById(anyLong())).willReturn(Optional.of(customer));

        customerServiceImpl.changeCustomerStatus(1L,CustomerStatus.BLOCKED);

        verify(customerRepository, times(1)).findById(anyLong());
        verify(customerRepository, times(1)).save(customer);
    }
}