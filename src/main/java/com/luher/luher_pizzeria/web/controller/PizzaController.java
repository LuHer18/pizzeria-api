package com.luher.luher_pizzeria.web.controller;

import com.luher.luher_pizzeria.persistence.entity.PizzaEntity;
import com.luher.luher_pizzeria.persistence.repository.PizzaRepository;
import com.luher.luher_pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll(){

        return ResponseEntity.ok(this.pizzaService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> getById(@PathVariable("id") int idPizza){
        return pizzaService.getById(idPizza)
                .map( pizza -> new ResponseEntity<>(pizza, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/available")
    public ResponseEntity<List<PizzaEntity>> getAvailable(){

        return ResponseEntity.ok(this.pizzaService.getAvailable());
    }

    @GetMapping("/name")
    public ResponseEntity<PizzaEntity> getByName(@RequestParam(name = "name") String name){
        return ResponseEntity.ok(this.pizzaService.getByName(name));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> create(@RequestBody PizzaEntity pizza){
        if(pizza.getIdPizza() ==  null || !this.pizzaService.exits(pizza.getIdPizza())) {
            return new ResponseEntity<>(this.pizzaService.save(pizza), HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }
    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza){
        if(pizza.getIdPizza() != null || this.pizzaService.exits(pizza.getIdPizza())  ){
            return new ResponseEntity<>(this.pizzaService.save(pizza), HttpStatus.OK);
        }else {
            return ResponseEntity.badRequest().build();
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int idPizza){
        if(this.pizzaService.exits(idPizza)){
            this.pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


}
