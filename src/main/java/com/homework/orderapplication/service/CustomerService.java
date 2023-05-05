package com.homework.orderapplication.service;

import com.homework.orderapplication.exception.CustomException;
import com.homework.orderapplication.model.Customer;
import com.homework.orderapplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomException("Customer with id " + id + " not found", HttpStatus.NOT_FOUND));
    }
}
