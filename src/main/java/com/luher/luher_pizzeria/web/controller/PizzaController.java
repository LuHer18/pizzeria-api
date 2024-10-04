package com.luher.luher_pizzeria.web.controller;

import com.luher.luher_pizzeria.persistence.entity.PizzaEntity;
import com.luher.luher_pizzeria.service.PizzaService;
import com.luher.luher_pizzeria.service.dto.UpdatePizzaPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam (defaultValue = "0") int page,
                                                    @RequestParam (defaultValue = "8") int elements){

        return ResponseEntity.ok(this.pizzaService.getAll(page, elements));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> getById(@PathVariable("id") int idPizza){
        return pizzaService.getById(idPizza)
                .map( pizza -> new ResponseEntity<>(pizza, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAvailable(@RequestParam (defaultValue = "0") int page,
                                                          @RequestParam (defaultValue = "8") int elements,
                                                          @RequestParam(defaultValue = "price") String sortBy,
                                                          @RequestParam(defaultValue = "ASC") String sortDirection
                                                          ){

        return ResponseEntity.ok(this.pizzaService.getAvailable(page, elements, sortBy, sortDirection));
    }

    @GetMapping("/name")
    public ResponseEntity<PizzaEntity> getByName(@RequestParam(name = "name") String name){
        return ResponseEntity.ok(this.pizzaService.getByName(name));
    }

    @GetMapping("/with")
    public ResponseEntity<List<PizzaEntity>> getWith(@RequestParam(name = "ingredient") String ingredient){

        return ResponseEntity.ok(this.pizzaService.getWithDescription(ingredient));
    }

    @GetMapping("/without")
    public ResponseEntity<List<PizzaEntity>> getWithout(@RequestParam(name = "ingredient") String ingredient){

        return ResponseEntity.ok(this.pizzaService.getWithout(ingredient));
    }
    @GetMapping("/cheapest")
    public ResponseEntity<List<PizzaEntity>> getChepesPizzas(@RequestParam(name = "price") double price){

        return ResponseEntity.ok(this.pizzaService.getCheapest(price));
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
    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPrice dto){
        if(this.pizzaService.exits(dto.getPizzaId())){
            this.pizzaService.updatePrice(dto);
            return  ResponseEntity.ok().build();
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
