package com.mks.crescentbites.service;

import com.mks.crescentbites.dto.RestaurantDto;
import com.mks.crescentbites.entity.Restaurant;
import com.mks.crescentbites.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceImplTest {
    @Mock
    private RestaurantRepository restaurantRepository;
    @InjectMocks
    private RestaurantServiceImpl restaurantServiceImpl;

    @Test
    public void shouldReturnAllRestaurants() {
        // Arrange
        Restaurant restaurant = Restaurant.builder()
                .id(1)
                .name("Restaurant 1")
                .city("City 1")
                .state("State 1")
                .address("Address 1")
                .zipCode("Zip Code 1")
                .build();

        Restaurant restaurant2 = Restaurant.builder()
                .id(2)
                .name("Restaurant 2")
                .city("City 2")
                .state("State 2")
                .address("Address 2")
                .zipCode("Zip Code 2")
                .build();

        List<Restaurant> restaurantList = List.of(restaurant, restaurant2);
        when(restaurantRepository.findAll()).thenReturn(restaurantList);

        // Act
        List<RestaurantDto> result = restaurantServiceImpl.getAllRestaurants();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("Restaurant 1");
        assertThat(result.get(0).getCity()).isEqualTo("City 1");
        assertThat(result.get(0).getState()).isEqualTo("State 1");
        assertThat(result.get(0).getAddress()).isEqualTo("Address 1");
        assertThat(result.get(0).getZipCode()).isEqualTo("Zip Code 1");
        assertThat(result.get(1).getName()).isEqualTo("Restaurant 2");
        assertThat(result.get(1).getCity()).isEqualTo("City 2");
        assertThat(result.get(1).getState()).isEqualTo("State 2");
        assertThat(result.get(1).getAddress()).isEqualTo("Address 2");
        assertThat(result.get(1).getZipCode()).isEqualTo("Zip Code 2");
    }


    @Test
    public void shouldReturnRestaurantByName() {
        // Arrange
        Restaurant restaurant = Restaurant.builder()
                .id(1)
                .name("Restaurant 1")
                .city("City 1")
                .state("State 1")
                .address("Address 1")
                .zipCode("Zip Code 1")
                .build();

        when(restaurantRepository.findRestaurantByName("Restaurant 1")).thenReturn(java.util.Optional.of(restaurant));

        // Act
        RestaurantDto result = restaurantServiceImpl.getRestaurantByName("Restaurant 1");

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Restaurant 1");
        assertThat(result.getCity()).isEqualTo("City 1");
        assertThat(result.getState()).isEqualTo("State 1");
        assertThat(result.getAddress()).isEqualTo("Address 1");
        assertThat(result.getZipCode()).isEqualTo("Zip Code 1");
    }

    @Test
    public void shouldThrowExceptionWhenRestaurantNotFound() {
        // Arrange
        when(restaurantRepository.findRestaurantByName("Restaurant 1")).thenReturn(java.util.Optional.empty());

        // Act and Assert
        try {
            restaurantServiceImpl.getRestaurantByName("Restaurant 1");
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("restaurant not found with name : Restaurant 1");
        }
    }

    @Test
    public void shouldAddRestaurant() {
        // Arrange
        RestaurantDto restaurantDto = RestaurantDto.builder()
                .name("Restaurant 1")
                .city("City 1")
                .state("State 1")
                .address("Address 1")
                .zipCode("Zip Code 1")
                .build();

        when(restaurantRepository.findRestaurantByName("Restaurant 1")).thenReturn(java.util.Optional.empty());

        // Act
        String result = restaurantServiceImpl.addRestaurant(restaurantDto);

        // Assert
        assertThat(result).isEqualTo("Restaurant has been added!!");
    }


    @Test
    public void shouldThrowExceptionWhenRestaurantAlreadyExists() {
        // Arrange
        RestaurantDto restaurantDto = RestaurantDto.builder()
                .name("Restaurant 1")
                .city("City 1")
                .state("State 1")
                .address("Address 1")
                .zipCode("Zip Code 1")
                .build();

        when(restaurantRepository.findRestaurantByName("Restaurant 1")).thenReturn(java.util.Optional.of(Restaurant.builder().build()));

        // Act and Assert
        try {
            restaurantServiceImpl.addRestaurant(restaurantDto);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Restaurant 1 already exists");
        }
    }


    @Test
    public void shouldUpdateRestaurant() {
        // Arrange
        RestaurantDto restaurantDto = RestaurantDto.builder()
                .name("Restaurant 1")
                .city("City 1")
                .state("State 1")
                .address("Address 1")
                .zipCode("Zip Code 1")
                .build();

        Restaurant restaurant = Restaurant.builder()
                .id(1)
                .name("Restaurant 1")
                .city("City 1")
                .state("State 1")
                .address("Address 1")
                .zipCode("Zip Code 1")
                .build();

        when(restaurantRepository.findRestaurantByName("Restaurant 1")).thenReturn(java.util.Optional.of(restaurant));

        // Act
        String result = restaurantServiceImpl.updateRestaurant(restaurantDto);

        // Assert
        assertThat(result).isEqualTo("Restaurant has been updated");
    }

    @Test
    public void shouldThrowExceptionWhenRestaurantToUpdateNotFound() {
        // Arrange
        RestaurantDto restaurantDto = RestaurantDto.builder()
                .name("Restaurant 1")
                .city("City 1")
                .state("State 1")
                .address("Address 1")
                .zipCode("Zip Code 1")
                .build();

        when(restaurantRepository.findRestaurantByName("Restaurant 1")).thenReturn(java.util.Optional.empty());

        // Act and Assert
        try {
            restaurantServiceImpl.updateRestaurant(restaurantDto);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("restaurant not found with id : Restaurant 1");
        }
    }


    @Test
    public void shouldDeleteRestaurant() {
        // Arrange
        Restaurant restaurant = Restaurant.builder()
                .id(1)
                .name("Restaurant 1")
                .city("City 1")
                .state("State 1")
                .address("Address 1")
                .zipCode("Zip Code 1")
                .build();

        when(restaurantRepository.findRestaurantByName("Restaurant 1")).thenReturn(java.util.Optional.of(restaurant));

        // Act
        String result = restaurantServiceImpl.deleteRestaurant("Restaurant 1");

        // Assert
        assertThat(result).isEqualTo("Restaurant has been removed");
    }

    @Test
    public void shouldThrowExceptionWhenRestaurantToDeleteNotFound() {
        // Arrange
        when(restaurantRepository.findRestaurantByName("Restaurant 1")).thenReturn(java.util.Optional.empty());

        // Act and Assert
        try {
            restaurantServiceImpl.deleteRestaurant("Restaurant 1");
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("restaurant not found with id : Restaurant 1");
        }
    }

}
