package com.mks.crescentbites.service;

import com.mks.crescentbites.dto.RestaurantDto;
import com.mks.crescentbites.entity.Restaurant;
import com.mks.crescentbites.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    public List<RestaurantDto> getAllRestaurants(){
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return  restaurantList.stream().map(restaurant -> convertToDto(restaurant)).toList();
    }


    private RestaurantDto convertToDto(Restaurant restaurant){
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setName(restaurant.getName());
        restaurantDto.setDescription(restaurant.getDescription());
        restaurantDto.setAddress(restaurant.getAddress());
        restaurantDto.setCity(restaurant.getCity());
        restaurantDto.setState(restaurant.getState());
        restaurantDto.setZipCode(restaurant.getZipCode());
        restaurantDto.setCuisineType(restaurant.getCuisineType());
        restaurantDto.setMenuImageUrl(restaurant.getMenuImageUrl());
        return restaurantDto;
    }
}
