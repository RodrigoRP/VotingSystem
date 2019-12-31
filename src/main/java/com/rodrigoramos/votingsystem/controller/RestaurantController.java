package com.rodrigoramos.votingsystem.controller;

import com.rodrigoramos.votingsystem.model.Restaurant;
import com.rodrigoramos.votingsystem.service.impl.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping(value = "/")
    public ResponseEntity<Void> insert(@RequestBody Restaurant restaurant) {
        restaurant = restaurantService.insert(restaurant);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(restaurant.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Restaurant>> findAll() {
        List<Restaurant> employeeList = restaurantService.findAll();
        return ResponseEntity.ok().body(employeeList);
    }
}
