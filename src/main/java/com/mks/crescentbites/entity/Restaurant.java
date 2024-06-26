package com.mks.crescentbites.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String address;
    private String city;

    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "cuisine_type")
    private String cuisineType;

    @Column(name = "owner_id")
    private int ownerId;

    @Column(name = "menu_image_url")
    private String menuImageUrl;

    @Column(name = "created_at")
    private Timestamp created_at;

    @Column(name = "updated_at")
    private Timestamp updated_at;
}

