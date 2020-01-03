package com.rodrigoramos.votingsystem.controller;

import com.rodrigoramos.votingsystem.service.impl.RestaurantService;
import com.rodrigoramos.votingsystem.service.impl.VoteService;
import com.rodrigoramos.votingsystem.service.interfaces.VoteServiceInterface;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "api/vote" )
public class VoteController {

    private VoteService voteService;
    private RestaurantService restaurantService;

    @Autowired
    public VoteController(VoteService voteService, RestaurantService restaurantService) {
        this.voteService = voteService;
        this.restaurantService = restaurantService;
    }


    @PutMapping
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Authorization",
                    required = true,
                    dataType = "string",
                    paramType = "header",
                    value = "Token de autenticação."
            )})
    public ResponseEntity<HttpStatus> vote(@RequestParam(value = "rest_id") Integer rest_id) {
        restaurantService.checkIfExist(rest_id);
        voteService.vote(rest_id, LocalDateTime.now(VoteServiceInterface.ZONE_ID));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/countAll")
    public long countAll(){
        return voteService.countAll();
    }

    @GetMapping(value = "/restaurants/{restaurantId}/count")
    public long countByRestaurant(@PathVariable("restaurantId") Integer restaurantId){
        return voteService.countAllByRestaurantId(restaurantId);
    }

    @GetMapping(value = "/winner")
    public long restaurantWinner(){
        return voteService.countWinnerRestaurant();
    }







}
