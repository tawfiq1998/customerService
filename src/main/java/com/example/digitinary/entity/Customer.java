package com.example.digitinary.entity;

import com.example.digitinary.entity.enums.CustomerStatus;
import com.example.digitinary.entity.enums.CustomerType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    @Column(columnDefinition = "NVARCHAR(250)")
    private String name;

    @Column(length = 7, nullable = false, columnDefinition = "NVARCHAR(100)")
    @Size(min = 7, max = 7, message = "Code must be exactly 7 characters long")
    private String legalId;
    private String legalIdType;
    private String address;
    private Date dateOfBirth;
    private String createdBy;
    private String modifiedBy;
    private Date creationDate;


    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    @Enumerated(EnumType.STRING)
    private CustomerStatus customerStatus;
}
