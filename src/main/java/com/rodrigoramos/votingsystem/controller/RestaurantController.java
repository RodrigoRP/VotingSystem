package com.rodrigoramos.votingsystem.controller;

import com.rodrigoramos.votingsystem.model.Restaurant;
import com.rodrigoramos.votingsystem.service.impl.RestaurantService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Authorization",
                    required = true,
                    dataType = "string",
                    paramType = "header",
                    value = "Token de autenticação."
            )})
    public ResponseEntity<Void> insert(@RequestBody Restaurant restaurant) {
        restaurant = restaurantService.insert(restaurant);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(restaurant.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Authorization",
                    required = true,
                    dataType = "string",
                    paramType = "header",
                    value = "Token de autenticação."
            )})
    public ResponseEntity<List<Restaurant>> findAll() {
        List<Restaurant> employeeList = restaurantService.findAll();
        return ResponseEntity.ok().body(employeeList);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value="Busca por id")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Authorization",
                    required = true,
                    dataType = "string",
                    paramType = "header",
                    value = "Token de autenticação."
            )})
    public ResponseEntity<Restaurant> find(@PathVariable Integer id) {
        Restaurant restaurant = restaurantService.findById(id);
        return ResponseEntity.ok().body(restaurant);
    }

}
