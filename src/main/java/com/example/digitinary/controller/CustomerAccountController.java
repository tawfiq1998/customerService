package com.example.digitinary.controller;

import com.example.digitinary.service.CustomerService;
import com.example.digitinary.kafka.request.CreateAccountRequestDTO;
import com.example.digitinary.util.ResponseMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("kafka")
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerAccountController {
    private final CustomerService customerService;
    @PostMapping("/createAccount")
    public ResponseEntity<ResponseMessage> createWithAccount(@Valid @RequestBody CreateAccountRequestDTO requestDTO) {
        customerService.createAccount(requestDTO);
        return ResponseEntity.ok(new ResponseMessage("Customer created successfully"));
    }

}
