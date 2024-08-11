package com.example.digitinary.service;

import com.example.digitinary.dto.request.CreateCustomerRequestDTO;
import com.example.digitinary.dto.request.EditCustomerRequestDTO;
import com.example.digitinary.dto.request.GetCustomersFilterRequestDTO;
import com.example.digitinary.dto.response.GetCustomerFilterResponseDTO;
import com.example.digitinary.dto.response.GetCustomersResponseDTO;
import com.example.digitinary.entity.enums.CustomerStatus;
import com.example.digitinary.filterRelated.PagingAbstractResponse;
import com.example.digitinary.kafka.request.CreateAccountRequestDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    void createCustomer(CreateCustomerRequestDTO createCustomerRequestDTO);
    void createAccount(CreateAccountRequestDTO createCustomerRequestDTO);
    void editCustomer(EditCustomerRequestDTO editCustomerRequestDTO);
    void changeCustomerStatus(Long id, CustomerStatus customerStatus);
    List<GetCustomersResponseDTO> getCustomers();
    PagingAbstractResponse<GetCustomerFilterResponseDTO>
    getFilteredCustomerList(int page,int numberOfRecords,Optional<GetCustomersFilterRequestDTO> getCustomersFilterRequestDTO);
}
