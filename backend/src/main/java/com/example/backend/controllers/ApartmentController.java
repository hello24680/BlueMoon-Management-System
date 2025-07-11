package com.example.backend.controllers;


import com.example.backend.dtos.ApartmentDTO;
import com.example.backend.dtos.subDTO.ApartmentDetailDTO;
import com.example.backend.dtos.subDTO.ApartmentSummaryDTO;
import com.example.backend.services.ApartmentService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apartments")
@RequiredArgsConstructor
public class ApartmentController {

    private final ApartmentService apartmentService;


    @GetMapping
    public ResponseEntity<Page<ApartmentSummaryDTO>> findAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                             @RequestParam(value = "limit", defaultValue = "20") int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        return ResponseEntity.ok(apartmentService.getAllApartments(pageable));
    }

    @PostMapping
    public ResponseEntity<ApartmentDTO> saveApartment(@RequestBody JsonNode data) {
        ApartmentDTO savedApartment = apartmentService.saveApartment(data);
        return new ResponseEntity<>(savedApartment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApartmentDTO> updateApartment(@PathVariable Long id, @RequestBody JsonNode data) {
        ApartmentDTO updatedApartment = apartmentService.updateApartment(data, id);
        return new ResponseEntity<>(updatedApartment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApartment(@PathVariable Long id) {
        apartmentService.deleteApartment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApartmentDetailDTO> getApartmentDetail(@PathVariable Long id) {
        ApartmentDetailDTO dto = apartmentService.getApartmentDetail(id);
        return ResponseEntity.ok(dto);
    }
}
