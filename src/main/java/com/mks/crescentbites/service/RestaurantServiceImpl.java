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
        return restaurantList.stream().map(this::convertToDto).toList();
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
        restaurant.setCreated_at(Timestamp.from(Instant.now()));
        restaurantRepository.save(restaurant);
        return "Restaurant has been added!!";
    }

    @Override
    public String updateRestaurant(RestaurantDto restaurantDto) {
        Restaurant retrievedRestaurant = restaurantRepository.findRestaurantByName(restaurantDto.getName()).orElseThrow(() ->
                new ResourceNotFoundException("restaurant", "id", restaurantDto.getName()));

        retrievedRestaurant.setDescription(restaurantDto.getDescription());
        retrievedRestaurant.setMenuImageUrl(restaurantDto.getMenuImageUrl());
        retrievedRestaurant.setUpdated_at(Timestamp.from(Instant.now()));
        restaurantRepository.save(retrievedRestaurant);
        return "Restaurant has been updated";
    }

    @Override
    public String deleteRestaurant(String restaurantName) {
        Restaurant retrievedRestaurant = restaurantRepository.findRestaurantByName(restaurantName).orElseThrow(() ->
                new ResourceNotFoundException("restaurant", "id", restaurantName));
        restaurantRepository.delete(retrievedRestaurant);
        return "Restaurant has been removed!!";
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
                .ownerId(restaurant.getOwnerId())
                .menuImageUrl(restaurant.getMenuImageUrl())
                .created_at(restaurant.getCreated_at())
                .updated_at(restaurant.getUpdated_at())
                .build();
    }


    private Restaurant convertToEntity(RestaurantDto restaurantDto) {
        return Restaurant.builder()
                .name(restaurantDto.getName())
                .description(restaurantDto.getDescription())
                .address(restaurantDto.getAddress())
                .city(restaurantDto.getCity())
                .state(restaurantDto.getState())
                .zipCode(restaurantDto.getZipCode())
                .cuisineType(restaurantDto.getCuisineType())
                .ownerId(restaurantDto.getOwnerId())
                .menuImageUrl(restaurantDto.getMenuImageUrl())
                .created_at(restaurantDto.getCreated_at())
                .updated_at(restaurantDto.getUpdated_at())
                .build();
    }
}
