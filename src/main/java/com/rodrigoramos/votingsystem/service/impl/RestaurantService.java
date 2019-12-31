package com.rodrigoramos.votingsystem.service.impl;

import com.rodrigoramos.votingsystem.model.Restaurant;
import com.rodrigoramos.votingsystem.repository.RestaurantRepository;
import com.rodrigoramos.votingsystem.service.interfaces.RestaurantServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService implements RestaurantServiceInterface {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant insert(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

}
