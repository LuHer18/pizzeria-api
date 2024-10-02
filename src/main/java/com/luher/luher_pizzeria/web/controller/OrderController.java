package com.luher.luher_pizzeria.web.controller;

import com.luher.luher_pizzeria.persistence.entity.OrderEntity;
import com.luher.luher_pizzeria.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll(){
        return ResponseEntity.ok(this.orderService.getAll());
    }

    @GetMapping("/{idOrder}")
    public  ResponseEntity<OrderEntity> getById(@PathVariable int idOrder){
        if(this.orderService.getById(idOrder) == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.orderService.getById(idOrder));
    }

    @GetMapping("/today")
    public  ResponseEntity<List<OrderEntity>> getTodayOrders(){
        return ResponseEntity.ok(this.orderService.getTodayOrders());
    }
    @GetMapping("/outsite")
    public  ResponseEntity<List<OrderEntity>> getOutsiteOrders(){
        return  ResponseEntity.ok(this.orderService.getOutsideOrders());
    }

}
