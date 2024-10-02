package com.luher.luher_pizzeria.service;

import com.luher.luher_pizzeria.persistence.entity.OrderEntity;
import com.luher.luher_pizzeria.persistence.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll(){
        List<OrderEntity> orders = this.orderRepository.findAll();
        orders.forEach(o-> System.out.println(o.getCustomer().getName()));
        return orders;
    }
    public OrderEntity getById( int idOrder){
        return this.orderRepository.findById(idOrder)
                .orElse(null);
    }
}
