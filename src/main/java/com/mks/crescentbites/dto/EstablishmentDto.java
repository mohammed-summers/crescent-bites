package com.mks.crescentbites.dto;

import com.mks.crescentbites.enums.EstablishmentType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class EstablishmentDto {
    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private EstablishmentType type;

    @NotEmpty
    private String cuisineType;

    @NotEmpty
    private String address;

    @NotEmpty
    private String city;

    @NotEmpty
    private String state;

    @NotEmpty
    private String zipCode;

    private Double latitude;

    private Double longitude;

    @Column(length = 20)
    private String phone;

    private String website;

    private String halalCertification;

    private Boolean prayerFacility;

    @Column(name = "operating_hours")
    private String operatingHours;

    @Column(name = "image_url")
    private String imageUrl;

    @NotEmpty
    private String menuImageUrl;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;
}
