package com.mks.crescentbites.controller;

import com.mks.crescentbites.dto.EstablishmentDto;
import com.mks.crescentbites.service.EstablishmentService;
import com.mks.crescentbites.service.EstablishmentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class EstablishmentController {
    private final EstablishmentService establishmentService;

    public EstablishmentController(EstablishmentServiceImpl establishmentService) {
        this.establishmentService = establishmentService;
    }

    @GetMapping("/establishment")
    public ResponseEntity<List<EstablishmentDto>> getAllEstablishment() {
        List<EstablishmentDto> establishmentDtoList = establishmentService.getAllEstablishments();
        return new ResponseEntity<>(establishmentDtoList, HttpStatus.OK);
    }

    @GetMapping("/establishment/{name}")
    public ResponseEntity<EstablishmentDto> getEstablishmentByName(@PathVariable("name") String establishmentName) {
        EstablishmentDto establishmentByName = establishmentService.getEstablishmentByName(establishmentName);
        return new ResponseEntity<>(establishmentByName, HttpStatus.OK);
    }

    @GetMapping("/establishment/filter")
    public ResponseEntity<List<EstablishmentDto>> filterEstablishment(@RequestParam(value = "name", required = false) String name,
                                                                      @RequestParam(value = "cuisineType", required = false) String cuisineType,
                                                                      @RequestParam(value = "city", required = false) String city,
                                                                      @RequestParam(value = "state", required = false) String state) {
        List<EstablishmentDto> establishmentDtoList = establishmentService.filterEstablishment(name, cuisineType, city, state);
        return new ResponseEntity<>(establishmentDtoList, HttpStatus.OK);
    }

    @PostMapping("/establishment")
    public ResponseEntity<String> addEstablishment(@Valid @RequestBody EstablishmentDto establishmentDto) {
        String response = establishmentService.addEstablishment(establishmentDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/establishment/{name}")
    public ResponseEntity<String> updateEstablishment(@Valid @RequestBody EstablishmentDto establishmentDto) {
        String response = establishmentService.updateEstablishment(establishmentDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/establishment/{name}")
    public ResponseEntity<String> deleteEstablishment(@PathVariable("name") String establishmentName) {
        String response = establishmentService.deleteEstablishment(establishmentName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
