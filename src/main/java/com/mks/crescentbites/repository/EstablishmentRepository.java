package com.mks.crescentbites.repository;

import com.mks.crescentbites.entity.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Integer> {
    Optional<Establishment> findEstablishmentByName(String name);

//    TODO: improve search fields by using wild cards
    @Query("SELECT r FROM Establishment r WHERE r.name = :name OR r.cuisineType = :cuisineType OR r.city = :city OR r.state = :state")
    List<Establishment> filterEstablishment(@Param("name") String name, @Param("cuisineType") String cuisineType, @Param("city") String city, @Param("state") String state);
}
