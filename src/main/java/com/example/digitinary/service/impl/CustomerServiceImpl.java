package com.example.digitinary.service.impl;

import com.example.digitinary.dto.request.CreateCustomerRequestDTO;
import com.example.digitinary.dto.request.EditCustomerRequestDTO;
import com.example.digitinary.dto.request.GetCustomersFilterRequestDTO;
import com.example.digitinary.dto.response.GetCustomerFilterResponseDTO;
import com.example.digitinary.dto.response.GetCustomersResponseDTO;
import com.example.digitinary.entity.Customer;
import com.example.digitinary.entity.enums.CustomerStatus;
import com.example.digitinary.exception.CustomerNotFoundException;
import com.example.digitinary.filterRelated.CustomerFilter;
import com.example.digitinary.filterRelated.FilterRepository;
import com.example.digitinary.filterRelated.PagingAbstractResponse;
import com.example.digitinary.repository.CustomerRepository;
import com.example.digitinary.service.CustomerService;
import com.example.digitinary.kafka.EventProducer;
import com.example.digitinary.kafka.request.CreateAccountRequestDTO;
import com.example.digitinary.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;

@Service
@Slf4j
@RequiredArgsConstructor
class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final FilterRepository filterRepository;
    private final RestTemplate restTemplate;
    private final EventProducer eventProducer;

    @Override
    public void createCustomer(CreateCustomerRequestDTO createCustomerRequestDTO) {
        log.info("Create customer request: {}", createCustomerRequestDTO);
        Customer customer = customerMapper.createCustomerDTOToCustomer(createCustomerRequestDTO);
        customer.setCustomerStatus(CustomerStatus.ACTIVE);
        customerRepository.save(customer);
        log.info("Customer created");
    }

    @Override
    public void createAccount(CreateAccountRequestDTO createCustomerRequestDTO) {
        log.info("Creating account with request: {}", createCustomerRequestDTO);
        eventProducer.sendEvent("Account",createCustomerRequestDTO);
    }



    @Override
    public void editCustomer(EditCustomerRequestDTO requestDTO) {
        log.info("Edit customer request: {}", requestDTO);

        Customer customer = findCustomerById(requestDTO.getId());

        Customer entity = customerMapper.editCustomerDTOToCustomer(requestDTO);
        entity.setCustomerStatus(customer.getCustomerStatus());

        customerRepository.save(entity);
        log.info("Customer edited");
    }


    @Override
    public void changeCustomerStatus(Long id, CustomerStatus customerStatus) {
        log.info("Change customer status: {} for id: {}", customerStatus, id);
        Customer customer = findCustomerById(id);
        customer.setCustomerStatus(customerStatus);
        customerRepository.save(customer);
        log.info("Customer status changed");
    }

    @Override
    public List<GetCustomersResponseDTO> getCustomers() {
        List<Customer> customers = customerRepository.getAllCustomers();
        return customers.stream()
                .map(c ->customerMapper.CustomerToGetCustomersResponseDTO(c))
                .toList();
    }


    @Override
    public PagingAbstractResponse<GetCustomerFilterResponseDTO> getFilteredCustomerList(int page,int numberOfRecords,Optional<GetCustomersFilterRequestDTO> getCustomersFilterRequestDTO) {
        GetCustomersFilterRequestDTO filter = getCustomersFilterRequestDTO.orElseGet(GetCustomersFilterRequestDTO::new);
        Specification<Customer> filteredResults = CustomerFilter.getCustomerSpecification(filter);

        Pageable pageable = PageRequest.of(--page, numberOfRecords, Sort.by("id").descending());
        Page<Customer> result = filterRepository.findAll(filteredResults, pageable);

        List<GetCustomerFilterResponseDTO> filteredResponse = result.getContent().stream().map(c
                ->customerMapper.CustomerToGetCustomersFilterRequestDTO(c)).toList();
        PagingAbstractResponse<GetCustomerFilterResponseDTO> pagingAbstractResponse = new PagingAbstractResponse();
        pagingAbstractResponse.setNumberOfPages(page);
        pagingAbstractResponse.setTotalElements(numberOfRecords);
        pagingAbstractResponse.setData(filteredResponse);
        System.out.println("Yes ------ ");
        return pagingAbstractResponse;
    }



    private Customer findCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> {
            String errorMessage = String.format("Customer with id %d does not exist", customerId);
            log.error(errorMessage);
            return new CustomerNotFoundException(errorMessage);
        });
    }
}