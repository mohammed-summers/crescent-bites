package com.mks.crescentbites.entity;

import com.mks.crescentbites.enums.EstablishmentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "establishment")
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EstablishmentType type;

    private String cuisineType;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, length = 2)
    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    private Double latitude;
    private Double longitude;

    @Column(length = 20)
    private String phone;

    private String website;

    @Column(name = "halal_certification")
    private String halalCertification;

    @Column(name = "prayer_facility")
    private Boolean prayerFacility;

    @Column(name = "operating_hours")
    private String operatingHours;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "menu_image_url")
    private String menuImageUrl;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
