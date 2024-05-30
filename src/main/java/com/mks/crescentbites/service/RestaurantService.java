package com.mks.crescentbites.service;

import com.mks.crescentbites.dto.RestaurantDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {
    List<RestaurantDto> getAllRestaurants();
    RestaurantDto getRestaurantByName(String restaurantName);
    String addRestaurant(RestaurantDto restaurantDto);
    String updateRestaurant(RestaurantDto restaurantDto);
    String deleteRestaurant(String restaurantName);
    List<RestaurantDto> filterRestaurants(String name, String cuisineType, String city, String state);
}
