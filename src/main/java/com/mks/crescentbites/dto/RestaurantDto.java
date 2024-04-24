package com.mks.crescentbites.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RestaurantDto {
    private String name;
    private String description;
    private String address;
    private String city;;
    private String state;
    private String zipCode;
    private String cuisineType;
    private int ownerId;
    private String menuImageUrl;
    private Timestamp created_at;
    private Timestamp updated_at;
}
