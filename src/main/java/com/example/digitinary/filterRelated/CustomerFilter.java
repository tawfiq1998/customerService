package com.example.digitinary.filterRelated;

import com.example.digitinary.dto.request.GetCustomersFilterRequestDTO;
import com.example.digitinary.entity.Customer;
import com.example.digitinary.entity.enums.CustomerStatus;
import com.example.digitinary.entity.enums.CustomerType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

public class CustomerFilter {
    public static Specification<Customer> getCustomerSpecification(GetCustomersFilterRequestDTO customerFilter) {
        return Specification
                .where(localDateAfter(customerFilter.getFromDateOfBirth()))
                .and(localDateBefore(customerFilter.getToDateOfBirth()))
                .and(addressesFilter(customerFilter.getAddresses()))
                .and(customerStatusFilter(customerFilter.getCustomerStatus()))
                .and(customerTypeFilter(customerFilter.getCustomerType()))
                .and(createdByFilter(customerFilter.getCreatedBy()))
                .and(modifiedByFilter(customerFilter.getModifiedBy()));
    }

    public static Specification<Customer> localDateAfter(Date date) {
        return (root, query, cb) -> date == null ? cb.conjunction() : cb.greaterThanOrEqualTo(root.get("creationDate"), date);
    }

    public static Specification<Customer> localDateBefore(Date date) {
        return (root, query, cb) -> date == null ? cb.conjunction() : cb.lessThanOrEqualTo(root.get("creationDate"), date);
    }

    public static Specification<Customer> addressesFilter(List<String> fieldValue) {
        return (root, query, cb) -> (fieldValue == null || fieldValue.isEmpty()) ? cb.conjunction() : root.get("address").in(fieldValue);
    }

    public static Specification<Customer> customerStatusFilter(CustomerStatus customerStatus) {
        return (root, query, cb) -> customerStatus == null ? cb.conjunction() : cb.equal(root.get("customerStatus"), customerStatus);
    }

    public static Specification<Customer> customerTypeFilter(CustomerType customerType) {
        return (root, query, cb) -> customerType == null ? cb.conjunction() : cb.equal(root.get("customerType"), customerType);
    }

    public static Specification<Customer> createdByFilter(String creatorName) {
        return (root, query, cb) -> !StringUtils.hasLength(creatorName) ? cb.conjunction() : cb.equal(root.get("createdBy"), creatorName);
    }

    public static Specification<Customer> modifiedByFilter(String modifierName) {
        return (root, query, cb) -> !StringUtils.hasLength(modifierName) ? cb.conjunction() : cb.equal(root.get("modifiedBy"), modifierName);
    }
}
