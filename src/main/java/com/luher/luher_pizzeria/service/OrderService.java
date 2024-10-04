package com.luher.luher_pizzeria.service;

import com.luher.luher_pizzeria.persistence.entity.OrderEntity;
import com.luher.luher_pizzeria.persistence.projection.OrderSummary;
import com.luher.luher_pizzeria.persistence.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ON_Site =  "S";

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

    public List<OrderEntity> getTodayOrders(){
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.orderRepository.findAllByDateAfter(today);
    }

    public  List<OrderEntity> getOutsideOrders(){
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        return this.orderRepository.findAllByMethodIn(methods);
    }

    public List<OrderEntity> getCustomerOrders(String idConstumer) {
        return this.orderRepository.findCustomerOrder(idConstumer);
    }
    public OrderSummary getSummary (int orderId){
        return this.orderRepository.findSummary(orderId);
    }
}
