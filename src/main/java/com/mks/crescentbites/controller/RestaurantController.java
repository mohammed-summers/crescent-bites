package com.mks.crescentbites.controller;

import com.mks.crescentbites.dto.RestaurantDto;
import com.mks.crescentbites.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RestaurantController {
    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurants")
    public ResponseEntity<RestaurantDto> getAllRestaurant(){
        List<RestaurantDto> restaurantDtoList = restaurantService.getAllRestaurants();
        return new ResponseEntity(restaurantDtoList, HttpStatus.OK);
    }
}
