package com.mks.crescentbites.dto;

import lombok.Data;

@Data
public class RestaurantDto {
    private String name;
    private String description;
    private String address;
    private String city;;
    private String state;
    private String zipCode;
    private String cuisineType;
    private String menuImageUrl;
}
