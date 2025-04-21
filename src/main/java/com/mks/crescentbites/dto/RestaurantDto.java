package com.mks.crescentbites.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class RestaurantDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String address;
    @NotEmpty
    private String city;
    @NotEmpty
    private String state;
    @NotEmpty
    private String zipCode;
    @NotEmpty
    private String cuisineType;
    @NotNull
    private int ownerId;
    @NotEmpty
    private String menuImageUrl;
    private Timestamp created_at;
    private Timestamp updated_at;
}
