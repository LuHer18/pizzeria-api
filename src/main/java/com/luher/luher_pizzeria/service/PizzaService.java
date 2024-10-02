package com.luher.luher_pizzeria.service;

import com.luher.luher_pizzeria.persistence.entity.PizzaEntity;
import com.luher.luher_pizzeria.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }


    public List<PizzaEntity> getAll(){

        return pizzaRepository.findAll();
    }

    public  List<PizzaEntity> getAvailable(){

        System.out.println(this.pizzaRepository.countByVeganTrue());
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    public PizzaEntity getByName(String name){
        return  this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name)
                .orElseThrow(()-> new RuntimeException("La pizza no exite"));
    }

    public  List<PizzaEntity>getWithDescription(String ingredient){
        return  this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }
    public  List<PizzaEntity>getCheapest(double price){
        return  this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public  List<PizzaEntity>getWithout(String ingredient){
        return  this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public Optional<PizzaEntity> getById(int idPizza){
        return pizzaRepository.findById(idPizza);
    }

    public PizzaEntity save( PizzaEntity pizza){
        return pizzaRepository.save(pizza);
    }

    public void  delete(int idPizza){
        this.pizzaRepository.deleteById(idPizza);
    }

    public boolean exits(int idPizza){
        return  pizzaRepository.existsById(idPizza);
    }



}
