package com.mks.crescentbites.controller;

import com.mks.crescentbites.dto.RestaurantDto;
import com.mks.crescentbites.service.RestaurantService;
import com.mks.crescentbites.service.RestaurantServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantServiceImpl restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurant() {
        List<RestaurantDto> restaurantDtoList = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurantDtoList, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{name}")
    public ResponseEntity<RestaurantDto> getRestaurantByName(@PathVariable("name") String restaurantName) {
        RestaurantDto restaurantByName = restaurantService.getRestaurantByName(restaurantName);
        return new ResponseEntity<>(restaurantByName, HttpStatus.OK);
    }

    @PostMapping("/restaurants")
    public ResponseEntity<String> addRestaurant(@RequestBody RestaurantDto restaurantDto) {
        String response = restaurantService.addRestaurant(restaurantDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/restaurant/{name}")
    public ResponseEntity<String> updateRestaurant(@RequestBody RestaurantDto restaurantDto){
        String response = restaurantService.updateRestaurant(restaurantDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/restaurant/{name}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable("name") String restaurantName) {
        String response = restaurantService.deleteRestaurant(restaurantName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
