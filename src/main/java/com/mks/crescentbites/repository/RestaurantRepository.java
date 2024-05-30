package com.mks.crescentbites.repository;

import com.mks.crescentbites.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    Optional<Restaurant> findRestaurantByName(String name);

    @Query("SELECT r FROM Restaurant r WHERE r.name = :name OR r.cuisineType = :cuisineType OR r.city = :city OR r.state = :state")
    List<Restaurant> filterRestaurant(@Param("name") String name, @Param("cuisineType") String cuisineType, @Param("city") String city, @Param("state") String state);
}
