package com.luher.luher_pizzeria.service;

import com.luher.luher_pizzeria.persistence.entity.CustomerEntity;
import com.luher.luher_pizzeria.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity getCustomerByPhone(String phone){
        return  this.customerRepository.findByPhone(phone);
    }
}
