package com.example.digitinary.filterRelated;

import com.example.digitinary.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterRepository extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer>{
}
