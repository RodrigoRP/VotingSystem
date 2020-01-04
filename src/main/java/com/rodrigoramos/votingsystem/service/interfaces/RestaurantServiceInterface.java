package com.rodrigoramos.votingsystem.service.interfaces;

import com.rodrigoramos.votingsystem.model.Restaurant;

import java.util.List;

public interface RestaurantServiceInterface extends ServiceInterface<Restaurant> {

    Restaurant insert(Restaurant restaurant);

    List<Restaurant> findAll();

    Restaurant findById(Integer id);

}
