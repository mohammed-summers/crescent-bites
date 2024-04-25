package com.mks.crescentbites.service;

import com.mks.crescentbites.dto.RestaurantDto;
import com.mks.crescentbites.entity.Restaurant;
import com.mks.crescentbites.exception.ResourceExists;
import com.mks.crescentbites.exception.ResourceNotFoundException;
import com.mks.crescentbites.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;


@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {
    RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    @Override
    public List<RestaurantDto> getAllRestaurants() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return restaurantList.stream().map(restaurant -> convertToDto(restaurant)).toList();
    }


    @Override
    public RestaurantDto getRestaurantByName(String restaurantName) {
        Restaurant restaurantByName = restaurantRepository.findRestaurantByName(restaurantName);
        if (restaurantByName == null) {
            throw new ResourceNotFoundException("restaurant", "id", restaurantName);
        }
        return convertToDto(restaurantByName);
    }

    @Override
    public String addRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = convertToEntity(restaurantDto);
        Restaurant restaurantByName = restaurantRepository.findRestaurantByName(restaurant.getName());
        if (restaurantByName != null) {
            throw new ResourceExists(restaurantDto.getName());
        }
        restaurantDto.setCreated_at(Timestamp.from(Instant.now()));
        restaurantRepository.save(convertToEntity(restaurantDto));
        return "Restaurant has been added!!";
    }

    @Override
    public String deleteRestaurant(String restaurantName) {
        Restaurant restaurantByName = restaurantRepository.findRestaurantByName(restaurantName);
        if (restaurantByName == null) {
            throw new ResourceNotFoundException("restaurant", "id", restaurantName);
        }
        restaurantRepository.deleteByName(restaurantName);
        return "Restaurant has been removed";
    }


    private RestaurantDto convertToDto(Restaurant restaurant) {
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


    private Restaurant convertToEntity(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDto.getName());
        restaurant.setDescription(restaurantDto.getDescription());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setCity(restaurantDto.getCity());
        restaurant.setState(restaurantDto.getState());
        restaurant.setZipCode(restaurantDto.getZipCode());
        restaurant.setCuisineType(restaurantDto.getCuisineType());
        restaurant.setOwnerId(restaurantDto.getOwnerId());
        restaurant.setMenuImageUrl(restaurantDto.getMenuImageUrl());
        restaurant.setCreated_at(restaurantDto.getCreated_at());
        return restaurant;
    }


}
