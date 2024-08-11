package com.example.digitinary.filterRelated;

import com.example.digitinary.dto.request.GetCustomersFilterRequestDTO;
import com.example.digitinary.entity.Customer;
import com.example.digitinary.entity.enums.CustomerStatus;
import com.example.digitinary.entity.enums.CustomerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerFilterTest {

    @Mock
    private Root<Customer> root;

    @Mock
    private CriteriaQuery<?> query;

    @Mock
    private CriteriaBuilder cb;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCustomerSpecification() {
        GetCustomersFilterRequestDTO filter = new GetCustomersFilterRequestDTO();
        filter.setFromDateOfBirth(new Date());
        filter.setToDateOfBirth(new Date());
        filter.setAddresses(List.of("Address1", "Address2"));
        filter.setCustomerStatus(CustomerStatus.ACTIVE);
        filter.setCustomerType(CustomerType.RETAIL);
        filter.setCreatedBy("John Doe");
        filter.setModifiedBy("Jane Doe");

        Specification<Customer> spec = CustomerFilter.getCustomerSpecification(filter);

        assertNotNull(spec);

    }

    @Test
    public void testLocalDateAfter_withDate() {
        Date date = new Date();
        Specification<Customer> spec = CustomerFilter.localDateAfter(date);

        spec.toPredicate(root, query, cb);
        verify(cb, times(1)).greaterThanOrEqualTo(root.get("creationDate"), date);
    }

    @Test
    public void testLocalDateAfter_nullDate() {
        Specification<Customer> spec = CustomerFilter.localDateAfter(null);

        spec.toPredicate(root, query, cb);
        verify(cb, times(1)).conjunction();
    }

    @Test
    public void testLocalDateBefore_withDate() {
        Date date = new Date();
        Specification<Customer> spec = CustomerFilter.localDateBefore(date);

        spec.toPredicate(root, query, cb);
        verify(cb, times(1)).lessThanOrEqualTo(root.get("creationDate"), date);
    }

    @Test
    public void testLocalDateBefore_nullDate() {
        Specification<Customer> spec = CustomerFilter.localDateBefore(null);

        spec.toPredicate(root, query, cb);
        verify(cb, times(1)).conjunction();
    }

    @Test
    public void testAddressesFilter_nullOrEmpty() {
        Specification<Customer> specNull = CustomerFilter.addressesFilter(null);
        Specification<Customer> specEmpty = CustomerFilter.addressesFilter(List.of());

        specNull.toPredicate(root, query, cb);
         specEmpty.toPredicate(root, query, cb);

        verify(cb, times(2)).conjunction();
    }

    @Test
    public void testCustomerStatusFilter_withStatus() {
        CustomerStatus status = CustomerStatus.ACTIVE;
        Specification<Customer> spec = CustomerFilter.customerStatusFilter(status);

        spec.toPredicate(root, query, cb);
        verify(cb, times(1)).equal(root.get("customerStatus"), status);
    }

    @Test
    public void testCustomerStatusFilter_nullStatus() {
        Specification<Customer> spec = CustomerFilter.customerStatusFilter(null);

        spec.toPredicate(root, query, cb);
        verify(cb, times(1)).conjunction();
    }

    @Test
    public void testCustomerTypeFilter_withType() {
        CustomerType type = CustomerType.RETAIL ;
        Specification<Customer> spec = CustomerFilter.customerTypeFilter(type);

        spec.toPredicate(root, query, cb);
        verify(cb, times(1)).equal(root.get("customerType"), type);
    }

    @Test
    public void testCustomerTypeFilter_nullType() {
        Specification<Customer> spec = CustomerFilter.customerTypeFilter(null);

        spec.toPredicate(root, query, cb);
        verify(cb, times(1)).conjunction();
    }

    @Test
    public void testCreatedByFilter_withCreator() {
        String creator = "John Doe";
        Specification<Customer> spec = CustomerFilter.createdByFilter(creator);

        spec.toPredicate(root, query, cb);
        verify(cb, times(1)).equal(root.get("createdBy"), creator);
    }

    @Test
    public void testCreatedByFilter_nullOrEmpty() {
        Specification<Customer> specNull = CustomerFilter.createdByFilter(null);
        Specification<Customer> specEmpty = CustomerFilter.createdByFilter("");

        specNull.toPredicate(root, query, cb);
         specEmpty.toPredicate(root, query, cb);

        verify(cb, times(2)).conjunction();
    }

    @Test
    public void testModifiedByFilter_withModifier() {
        String modifier = "Jane Doe";
        Specification<Customer> spec = CustomerFilter.modifiedByFilter(modifier);

        spec.toPredicate(root, query, cb);
        verify(cb, times(1)).equal(root.get("modifiedBy"), modifier);
    }

    @Test
    public void testModifiedByFilter_nullOrEmpty() {
        Specification<Customer> specNull = CustomerFilter.modifiedByFilter(null);
        Specification<Customer> specEmpty = CustomerFilter.modifiedByFilter("");

        specNull.toPredicate(root, query, cb);
         specEmpty.toPredicate(root, query, cb);

        verify(cb, times(2)).conjunction();
    }
}