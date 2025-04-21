package com.mks.crescentbites.service;

import com.mks.crescentbites.dto.EstablishmentDto;
import com.mks.crescentbites.entity.Establishment;
import com.mks.crescentbites.entity.Establishment;
import com.mks.crescentbites.repository.EstablishmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EstablishmentServiceImplTest {
    @Mock
    private EstablishmentRepository establishmentRepository;
    @InjectMocks
    private EstablishmentServiceImpl EstablishmentServiceImpl;

    @Test
    public void shouldReturnAllEstablishments() {
        // Arrange
        Establishment establishment1 = Establishment.builder()
                .id(1L)
                .name("Establishment 1")
                .city("City 1")
                .state("State 1")
                .address("Address 1")
                .zipCode("Zip Code 1")
                .build();

        Establishment establishment2 = Establishment.builder()
                .id(2L)
                .name("Establishment 2")
                .city("City 2")
                .state("State 2")
                .address("Address 2")
                .zipCode("Zip Code 2")
                .build();

        List<Establishment> EstablishmentList = List.of(establishment1, establishment2);
        when(establishmentRepository.findAll()).thenReturn(EstablishmentList);

        // Act
        List<EstablishmentDto> result = EstablishmentServiceImpl.getAllEstablishments();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("Establishment 1");
        assertThat(result.get(0).getCity()).isEqualTo("City 1");
        assertThat(result.get(0).getState()).isEqualTo("State 1");
        assertThat(result.get(0).getAddress()).isEqualTo("Address 1");
        assertThat(result.get(0).getZipCode()).isEqualTo("Zip Code 1");
        assertThat(result.get(1).getName()).isEqualTo("Establishment 2");
        assertThat(result.get(1).getCity()).isEqualTo("City 2");
        assertThat(result.get(1).getState()).isEqualTo("State 2");
        assertThat(result.get(1).getAddress()).isEqualTo("Address 2");
        assertThat(result.get(1).getZipCode()).isEqualTo("Zip Code 2");
    }


    @Test
    public void shouldReturnEstablishmentByName() {
        // Arrange
        Establishment establishment = Establishment.builder()
                .id(1L)
                .name("Establishment 1")
                .city("City 1")
                .state("State 1")
                .address("Address 1")
                .zipCode("Zip Code 1")
                .build();

        when(establishmentRepository.findEstablishmentByName("Establishment 1")).thenReturn(java.util.Optional.of(establishment));

        // Act
        EstablishmentDto result = EstablishmentServiceImpl.getEstablishmentByName("Establishment 1");

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Establishment 1");
        assertThat(result.getCity()).isEqualTo("City 1");
        assertThat(result.getState()).isEqualTo("State 1");
        assertThat(result.getAddress()).isEqualTo("Address 1");
        assertThat(result.getZipCode()).isEqualTo("Zip Code 1");
    }

    @Test
    public void shouldThrowExceptionWhenEstablishmentNotFound() {
        // Arrange
        when(establishmentRepository.findEstablishmentByName("Establishment 1")).thenReturn(java.util.Optional.empty());

        // Act and Assert
        try {
            EstablishmentServiceImpl.getEstablishmentByName("Establishment 1");
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Establishment not found with name : Establishment 1");
        }
    }

    @Test
    public void shouldAddEstablishment() {
        // Arrange
        EstablishmentDto establishmentDto = EstablishmentDto.builder()
                .name("Establishment 1")
                .city("City 1")
                .state("State 1")
                .address("Address 1")
                .zipCode("Zip Code 1")
                .build();

        when(establishmentRepository.findEstablishmentByName("Establishment 1")).thenReturn(java.util.Optional.empty());

        // Act
        String result = EstablishmentServiceImpl.addEstablishment(establishmentDto);

        // Assert
        assertThat(result).isEqualTo("Establishment has been added!!");
    }


    @Test
    public void shouldThrowExceptionWhenEstablishmentAlreadyExists() {
        // Arrange
        EstablishmentDto establishmentDto = EstablishmentDto.builder()
                .name("Establishment 1")
                .city("City 1")
                .state("State 1")
                .address("Address 1")
                .zipCode("Zip Code 1")
                .build();

        when(establishmentRepository.findEstablishmentByName("Establishment 1")).thenReturn(java.util.Optional.of(Establishment.builder().build()));

        // Act and Assert
        try {
            EstablishmentServiceImpl.addEstablishment(establishmentDto);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Establishment 1 already exists");
        }
    }


    @Test
    public void shouldUpdateEstablishment() {
        // Arrange
        EstablishmentDto establishmentDto = EstablishmentDto.builder()
                .name("Establishment 1")
                .city("City 1")
                .state("State 1")
                .address("Address 1")
                .zipCode("Zip Code 1")
                .build();

        Establishment establishment = Establishment.builder()
                .id(1L)
                .name("Establishment 1")
                .city("City 1")
                .state("State 1")
                .address("Address 1")
                .zipCode("Zip Code 1")
                .build();

        when(establishmentRepository.findEstablishmentByName("Establishment 1")).thenReturn(java.util.Optional.of(establishment));

        // Act
        String result = EstablishmentServiceImpl.updateEstablishment(establishmentDto);

        // Assert
        assertThat(result).isEqualTo("Establishment has been updated");
    }

    @Test
    public void shouldThrowExceptionWhenEstablishmentToUpdateNotFound() {
        // Arrange
        EstablishmentDto establishmentDto = EstablishmentDto.builder()
                .name("Establishment 1")
                .city("City 1")
                .state("State 1")
                .address("Address 1")
                .zipCode("Zip Code 1")
                .build();

        when(establishmentRepository.findEstablishmentByName("Establishment 1")).thenReturn(java.util.Optional.empty());

        // Act and Assert
        try {
            EstablishmentServiceImpl.updateEstablishment(establishmentDto);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Establishment not found with id : Establishment 1");
        }
    }


    @Test
    public void shouldDeleteEstablishment() {
        // Arrange
        Establishment establishment = Establishment.builder()
                .id(1L)
                .name("Establishment 1")
                .city("City 1")
                .state("State 1")
                .address("Address 1")
                .zipCode("Zip Code 1")
                .build();

        when(establishmentRepository.findEstablishmentByName("Establishment 1")).thenReturn(java.util.Optional.of(establishment));

        // Act
        String result = EstablishmentServiceImpl.deleteEstablishment("Establishment 1");

        // Assert
        assertThat(result).isEqualTo("Establishment has been removed!!");
    }

    @Test
    public void shouldThrowExceptionWhenEstablishmentToDeleteNotFound() {
        // Arrange
        when(establishmentRepository.findEstablishmentByName("Establishment 1")).thenReturn(java.util.Optional.empty());

        // Act and Assert
        try {
            EstablishmentServiceImpl.deleteEstablishment("Establishment 1");
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Establishment not found with id : Establishment 1");
        }
    }

}
