package com.mks.crescentbites.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurant")
public class Restaurant {
    @Id
    private Integer id;
    private String name;
    private String description;
    private String address;
    private String city;;
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

