package com.luher.luher_pizzeria.web.controller;

import com.luher.luher_pizzeria.persistence.entity.CustomerEntity;
import com.luher.luher_pizzeria.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerEntity> getByPhone(@RequestParam(name = "phone")  String phone){
        return ResponseEntity.ok(this.customerService.getCustomerByPhone(phone));
    }
}
