package com.example.digitinary.controller;

import com.example.digitinary.dto.request.CreateCustomerRequestDTO;
import com.example.digitinary.dto.request.EditCustomerRequestDTO;
import com.example.digitinary.dto.request.GetCustomersFilterRequestDTO;
import com.example.digitinary.dto.response.GetCustomerFilterResponseDTO;
import com.example.digitinary.dto.response.GetCustomersResponseDTO;
import com.example.digitinary.entity.enums.CustomerStatus;
import com.example.digitinary.filterRelated.PagingAbstractResponse;
import com.example.digitinary.service.CustomerService;
import com.example.digitinary.service.kafka.request.CreateAccountRequestDTO;
import com.example.digitinary.util.ResponseMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createCustomer(@Valid @RequestBody CreateCustomerRequestDTO requestDTO) {
        customerService.createCustomer(requestDTO);
        return ResponseEntity.ok(new ResponseMessage("Customer created successfully"));
    }

    @PostMapping("/edit")
    public ResponseEntity<ResponseMessage> editCustomer(@Valid @RequestBody EditCustomerRequestDTO requestDTO) {
        customerService.editCustomer(requestDTO);
        return ResponseEntity.ok(new ResponseMessage("Customer edited successfully"));
    }


    @PostMapping("/changeStatus")
    public ResponseEntity<ResponseMessage> changeCustomerStatus(@Param("id") Long CustomerId,@RequestBody CustomerStatus status) {
        customerService.changeCustomerStatus(CustomerId,status);
        return ResponseEntity.ok(new ResponseMessage("Customer status changed successfully"));
    }
    @PostMapping("/getCustomersWithFilteration")
    public ResponseEntity<PagingAbstractResponse<GetCustomerFilterResponseDTO>> getCustomersWithFiltration(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int numberOfRecords,
            @RequestBody Optional<GetCustomersFilterRequestDTO> getCustomersFilterRequestDTO){
        return ResponseEntity.ok(customerService.getFilteredCustomerList(page,numberOfRecords,getCustomersFilterRequestDTO));
    }
    @GetMapping("/getCustomers")
    public ResponseEntity<List<GetCustomersResponseDTO>> getCustomers(){
        return ResponseEntity.ok(customerService.getCustomers());
    }
}
