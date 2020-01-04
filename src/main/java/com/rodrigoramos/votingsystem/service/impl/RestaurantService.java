package com.rodrigoramos.votingsystem.service.impl;

import com.rodrigoramos.votingsystem.model.Restaurant;
import com.rodrigoramos.votingsystem.repository.RestaurantRepository;
import com.rodrigoramos.votingsystem.service.exception.ObjectNotFoundException;
import com.rodrigoramos.votingsystem.service.interfaces.RestaurantServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService implements RestaurantServiceInterface {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant insert(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public void checkIfExist(Integer id) {
        restaurantRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Restaurant.class.getName()));

    }

    @Override
    public Restaurant findById(Integer id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return restaurant.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Restaurant.class.getName()));
    }
}
