package com.example.Java_May_Spring.service;

import com.example.Java_May_Spring.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface CustomerService {
    Optional<Customer> getCustomerById(int id);
}
