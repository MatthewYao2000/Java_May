package com.example.Java_May_Spring.service.impl;

import com.example.Java_May_Spring.entity.Customer;
import com.example.Java_May_Spring.repo.CustomerRepo;
import com.example.Java_May_Spring.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomerServiceImpl implements CustomerService
{
    private final CustomerRepo customerRepo;
    public CustomerServiceImpl(CustomerRepo customerRepo){
        this.customerRepo = customerRepo;
    }
    @Override
    public Optional<Customer> getCustomerById(int id) {
        Optional<Customer> customer = customerRepo.findById(id);
        return customer;
    }
}
