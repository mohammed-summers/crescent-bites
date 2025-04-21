package com.mks.crescentbites.service;

import com.mks.crescentbites.dto.EstablishmentDto;
import com.mks.crescentbites.entity.Establishment;
import com.mks.crescentbites.exception.ResourceAlreadyExistsException;
import com.mks.crescentbites.exception.ResourceNotFoundException;
import com.mks.crescentbites.repository.EstablishmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class EstablishmentServiceImpl implements EstablishmentService {
    private final EstablishmentRepository establishmentRepository;

    public EstablishmentServiceImpl(EstablishmentRepository establishmentRepository) {
        this.establishmentRepository = establishmentRepository;
    }

    @Override
    public List<EstablishmentDto> getAllEstablishments() {
        List<Establishment> establishmentList = establishmentRepository.findAll();
        return establishmentList.stream().map(this::convertToDto).toList();
    }

    @Override
    public EstablishmentDto getEstablishmentByName(String establishmentName) {
        Establishment retrievedEstablishment = establishmentRepository.findEstablishmentByName(establishmentName).orElseThrow(() ->
                new ResourceNotFoundException("Establishment", "name", establishmentName));
        return convertToDto(retrievedEstablishment);
    }

    //TODO: ensure that you can add multiple Establishments with same name
    @Override
    public String addEstablishment(EstablishmentDto establishmentDto) {
        Establishment establishment = convertToEntity(establishmentDto);
        Optional<Establishment> EstablishmentByName = establishmentRepository.findEstablishmentByName(establishment.getName());
        if (EstablishmentByName.isPresent()) {
            throw new ResourceAlreadyExistsException(establishmentDto.getName());
        }
//        establishment.setCreatedAt(Timestamp.from(Instant.now())); // CHECK ENTITY
        establishmentRepository.save(establishment);
        return "Establishment has been added!!";
    }

    @Override
    public String updateEstablishment(EstablishmentDto establishmentDto) {
        Establishment retrievedEstablishment = establishmentRepository.findEstablishmentByName(establishmentDto.getName()).orElseThrow(() ->
                new ResourceNotFoundException("Establishment", "id", establishmentDto.getName()));

        retrievedEstablishment.setDescription(establishmentDto.getDescription());
        retrievedEstablishment.setImageUrl(establishmentDto.getMenuImageUrl());
//        retrievedEstablishment.setUpdatedAt(Timestamp.from(Instant.now())); -- CHECK ENTITY
        establishmentRepository.save(retrievedEstablishment);
        return "Establishment has been updated";
    }

    @Override
    public String deleteEstablishment(String EstablishmentName) {
        Establishment retrievedEstablishment = establishmentRepository.findEstablishmentByName(EstablishmentName).orElseThrow(() ->
                new ResourceNotFoundException("Establishment", "id", EstablishmentName));
        establishmentRepository.delete(retrievedEstablishment);
        return "Establishment has been removed!!";
    }

    @Override
    public List<EstablishmentDto> filterEstablishment(String name, String cuisineType, String city, String state) {
        return establishmentRepository.filterEstablishment(name, cuisineType, city, state).stream().map(this::convertToDto).toList();
    }

    private EstablishmentDto convertToDto(Establishment Establishment) {
        return EstablishmentDto.builder()
                .name(Establishment.getName())
                .description(Establishment.getDescription())
                .address(Establishment.getAddress())
                .city(Establishment.getCity())
                .state(Establishment.getState())
                .zipCode(Establishment.getZipCode())
                .cuisineType(Establishment.getCuisineType())
                .menuImageUrl(Establishment.getImageUrl())
                .created_at(Establishment.getCreatedAt())
                .updated_at(Establishment.getUpdatedAt())
                .build();
    }

    private Establishment convertToEntity(EstablishmentDto establishmentDto) {
        return Establishment.builder()
                .name(establishmentDto.getName())
                .description(establishmentDto.getDescription())
                .address(establishmentDto.getAddress())
                .city(establishmentDto.getCity())
                .state(establishmentDto.getState())
                .zipCode(establishmentDto.getZipCode())
                .cuisineType(establishmentDto.getCuisineType())
                .imageUrl(establishmentDto.getMenuImageUrl())
                .createdAt(establishmentDto.getCreated_at())
                .updatedAt(establishmentDto.getUpdated_at())
                .build();
    }
}
