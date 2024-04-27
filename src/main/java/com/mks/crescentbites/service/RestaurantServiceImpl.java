package com.mks.crescentbites.service;

import com.mks.crescentbites.dto.RestaurantDto;
import com.mks.crescentbites.entity.Restaurant;
import com.mks.crescentbites.exception.ResourceAlreadyExistsException;
import com.mks.crescentbites.exception.ResourceNotFoundException;
import com.mks.crescentbites.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;


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
        Restaurant retrievedRestaurant = restaurantRepository.findRestaurantByName(restaurantName).orElseThrow(() ->
                new ResourceNotFoundException("restaurant", "name", restaurantName));
        return convertToDto(retrievedRestaurant);
    }

    //TODO: ensure that you can add multiple restaurants with same name
    @Override
    public String addRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = convertToEntity(restaurantDto);
        Optional<Restaurant> restaurantByName = restaurantRepository.findRestaurantByName(restaurant.getName());
        if (restaurantByName.isPresent()) {
            throw new ResourceAlreadyExistsException(restaurantDto.getName());
        }
        restaurantDto.setCreated_at(Timestamp.from(Instant.now()));
        restaurantRepository.save(convertToEntity(restaurantDto));
        return "Restaurant has been added!!";
    }

    @Override
    public String updateRestaurant(RestaurantDto restaurantDto) {
        Restaurant retrievedRestaurant = restaurantRepository.findRestaurantByName(restaurantDto.getName()).orElseThrow(() ->
                new ResourceNotFoundException("restaurant", "id", restaurantDto.getName()));

        retrievedRestaurant.setDescription(restaurantDto.getDescription());
        retrievedRestaurant.setMenuImageUrl(restaurantDto.getMenuImageUrl());
        restaurantRepository.save(retrievedRestaurant);
        return "Restaurant has been updated";
    }

    @Override
    public String deleteRestaurant(String restaurantName) {
        Restaurant retrievedRestaurant = restaurantRepository.findRestaurantByName(restaurantName).orElseThrow(() ->
                new ResourceNotFoundException("restaurant", "id", restaurantName));
        restaurantRepository.delete(retrievedRestaurant);
        return "Restaurant has been removed";
    }


    private RestaurantDto convertToDto(Restaurant restaurant) {
        return RestaurantDto.builder()
                .name(restaurant.getName())
                .description(restaurant.getDescription())
                .address(restaurant.getAddress())
                .city(restaurant.getCity())
                .state(restaurant.getState())
                .zipCode(restaurant.getZipCode())
                .cuisineType(restaurant.getCuisineType())
                .menuImageUrl(restaurant.getMenuImageUrl())
                .created_at(restaurant.getCreated_at())
                .build();
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
