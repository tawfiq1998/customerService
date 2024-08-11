package com.example.digitinary.service.mapper;

import com.example.digitinary.dto.request.CreateCustomerRequestDTO;
import com.example.digitinary.dto.request.EditCustomerRequestDTO;
import com.example.digitinary.dto.request.GetCustomersFilterRequestDTO;
import com.example.digitinary.dto.response.GetCustomerFilterResponseDTO;
import com.example.digitinary.dto.response.GetCustomersResponseDTO;
import com.example.digitinary.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    Customer createCustomerDTOToCustomer(CreateCustomerRequestDTO createCustomerRequestDTO);

    Customer editCustomerDTOToCustomer(EditCustomerRequestDTO createCustomerRequestDTO);

    GetCustomersResponseDTO CustomerToGetCustomersResponseDTO(Customer customer);
    GetCustomerFilterResponseDTO CustomerToGetCustomersFilterRequestDTO(Customer customer);

}
