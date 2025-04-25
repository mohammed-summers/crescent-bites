package com.mks.crescentbites.controller;

import com.mks.crescentbites.dto.EstablishmentDto;
import com.mks.crescentbites.service.EstablishmentService;
import com.mks.crescentbites.service.EstablishmentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@Tag(name = "Establishment API")
public class EstablishmentController {
    private final EstablishmentService establishmentService;

    public EstablishmentController(EstablishmentServiceImpl establishmentService) {
        this.establishmentService = establishmentService;
    }

    @GetMapping("/establishments")
    @Operation(summary = "Get all establishments", description = "Returns all establishment")
    public ResponseEntity<List<EstablishmentDto>> getAllEstablishment() {
        List<EstablishmentDto> establishmentDtoList = establishmentService.getAllEstablishments();
        return new ResponseEntity<>(establishmentDtoList, HttpStatus.OK);
    }

    @GetMapping("/establishments/{establishmentName}")
    @Operation(summary = "Get a establishment by name", description = "Returns a establishment as per the name")
    public ResponseEntity<EstablishmentDto> getEstablishmentByName(@PathVariable("establishmentName") String establishmentName) {
        EstablishmentDto establishmentByName = establishmentService.getEstablishmentByName(establishmentName);
        return new ResponseEntity<>(establishmentByName, HttpStatus.OK);
    }

    @GetMapping("/establishments/search")
    @Operation(summary = "Search an establishment", description = "Search an establishment using valid parameters")
    public ResponseEntity<List<EstablishmentDto>> searchEstablishment(@RequestParam(value = "Name", required = false) String name,
                                                                      @RequestParam(value = "Cuisine Type", required = false) String cuisineType,
                                                                      @RequestParam(value = "City", required = false) String city,
                                                                      @RequestParam(value = "State", required = false) String state) {
        List<EstablishmentDto> establishmentDtoList = establishmentService.filterEstablishment(name, cuisineType, city, state);
        return new ResponseEntity<>(establishmentDtoList, HttpStatus.OK);
    }

    @PostMapping("/admin/establishments")
    @Operation(summary = "Add an establishment", description = "Add an establishment given a valid establishmentDto")
    public ResponseEntity<String> addEstablishment(@Valid @RequestBody EstablishmentDto establishmentDto) {
        String response = establishmentService.addEstablishment(establishmentDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/admin/establishment/{establishmentName}")
    @Operation(summary = "Update an establishment", description = "Update an establishment given a valid establishmentDto")
    public ResponseEntity<String> updateEstablishment(@Valid @RequestBody EstablishmentDto establishmentDto) {
        String response = establishmentService.updateEstablishment(establishmentDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/admin/establishments/{establishmentName}")
    @Operation(summary = "Delete an establishment by name", description = "Deletes a establishment as per the name")
    public ResponseEntity<String> deleteEstablishment(@PathVariable("establishmentName") String establishmentName) {
        String response = establishmentService.deleteEstablishment(establishmentName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
