package com.luher.luher_pizzeria.service.dto;

import lombok.Data;

@Data
public class UpdatePizzaPrice {
    private int pizzaId;
    private double newPrice;
}
