package com.luher.luher_pizzeria.web.controller;

import com.luher.luher_pizzeria.persistence.entity.CustomerEntity;
import com.luher.luher_pizzeria.persistence.entity.OrderEntity;
import com.luher.luher_pizzeria.service.CustomerService;
import com.luher.luher_pizzeria.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final OrderService orderService;

    @Autowired
    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<CustomerEntity> getByPhone(@RequestParam(name = "phone")  String phone){
        return ResponseEntity.ok(this.customerService.getCustomerByPhone(phone));
    }

    @GetMapping("/customers")
    public  ResponseEntity<List<OrderEntity>> getCostumerOrders(@RequestParam(name = "idCustomer") String idCustomer){
        return  ResponseEntity.ok(this.orderService.getCustomerOrders(idCustomer));
    }
}
