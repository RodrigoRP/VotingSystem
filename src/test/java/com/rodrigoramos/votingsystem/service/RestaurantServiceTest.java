package com.rodrigoramos.votingsystem.service;


import com.rodrigoramos.votingsystem.model.Restaurant;
import com.rodrigoramos.votingsystem.repository.RestaurantRepository;
import com.rodrigoramos.votingsystem.service.impl.RestaurantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;



@ContextConfiguration
@RunWith(SpringRunner.class)
public class RestaurantServiceTest {

    @Mock
    RestaurantRepository restaurantRepository;

    @InjectMocks
    RestaurantService restaurantService;



    @Test
    public void shouldSaveRestaurant() {
        Restaurant validRestaurant = new Restaurant(1, "Carlo e Camilla in Segheria", "Barbecue", Boolean.FALSE);
        restaurantService.insert(validRestaurant);
        verify(restaurantRepository, times(1)).save(validRestaurant);
    }

    @Test
    public void getAllEmployeesTest()
    {
        List<Restaurant> list = new ArrayList<Restaurant>();
        Restaurant empOne = new Restaurant(1, "Carlo e Camilla in Segheria", "Barbecue", Boolean.FALSE);
        Restaurant empTwo = new Restaurant(2, "Carlo e Camilla in ", "Barbecue", Boolean.FALSE);
        Restaurant empThree = new Restaurant(3, "Carlo e C", "Barbecue", Boolean.FALSE);

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(restaurantRepository.findAll()).thenReturn(list);

        List<Restaurant> restaurantList = restaurantService.findAll();

        assertEquals(3, restaurantList.size());
        verify(restaurantRepository, times(1)).findAll();
    }

    @Test
    public void findsAnyUserByIdWhenAdmin() {
        final Restaurant expected = new Restaurant(1, "Carlo e Camilla in Segheria", "Barbecue", Boolean.FALSE);
        when(restaurantRepository.findById(1)).thenReturn(Optional.of((expected)));

        final Restaurant actual = restaurantService.findById(1);
        assertThat(expected).isEqualToComparingFieldByField(actual);
    }

/*
    @Test
    public void returnsTrueWhenRequestedEmailExists() {
        when(restaurantService.checkIfExist(1)).thenReturn(Boolean.TRUE);
        Assert.assertTrue(restaurantService.checkIfExist(1));
    }
*/





}
