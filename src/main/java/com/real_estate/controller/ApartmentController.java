package com.real_estate.controller;

import com.real_estate.model.dto.request.CreateApartmentRequest;
import com.real_estate.model.dto.response.ApartmentResponseDTO;
import com.real_estate.model.dto.response.BaseResponseDTO;
import com.real_estate.service.ApartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/apartments")
@RequiredArgsConstructor
public class ApartmentController {
    private final ApartmentService apartmentService;

    @PostMapping
    public ResponseEntity<BaseResponseDTO<ApartmentResponseDTO>> createApartment(
            @Valid @RequestBody CreateApartmentRequest request) {

        ApartmentResponseDTO apartment = apartmentService.createApartment(request);

        return ResponseEntity.ok(
                BaseResponseDTO.<ApartmentResponseDTO>builder()
                        .success(true)
                        .message("Create apartment successfully")
                        .data(apartment)
                        .build());
    }

    @GetMapping
    public ResponseEntity<BaseResponseDTO<Page<ApartmentResponseDTO>>> getApartments(
            Pageable pageable) {

        Page<ApartmentResponseDTO> apartments = apartmentService.getApartments(pageable);

        return ResponseEntity.ok(
                BaseResponseDTO.<Page<ApartmentResponseDTO>>builder()
                        .success(true)
                        .message("Get apartments successfully")
                        .data(apartments)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<ApartmentResponseDTO>> getApartmentById(
            @PathVariable Long id) {

        ApartmentResponseDTO apartment = apartmentService.getApartmentById(id);

        return ResponseEntity.ok(
                BaseResponseDTO.<ApartmentResponseDTO>builder()
                        .success(true)
                        .message("Get apartment successfully")
                        .data(apartment)
                        .build());
    }

    @GetMapping("/building/{buildingId}")
    public ResponseEntity<BaseResponseDTO<Page<ApartmentResponseDTO>>> getApartmentsByBuilding(
            @PathVariable Long buildingId, Pageable pageable) {

        Page<ApartmentResponseDTO> apartments =
                apartmentService.getApartmentsByBuildingId(buildingId, pageable);

        return ResponseEntity.ok(
                BaseResponseDTO.<Page<ApartmentResponseDTO>>builder()
                        .success(true)
                        .message("Get apartments successfully")
                        .data(apartments)
                        .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<ApartmentResponseDTO>> updateApartment(
            @PathVariable Long id, @Valid @RequestBody CreateApartmentRequest request) {

        ApartmentResponseDTO apartment = apartmentService.updateApartment(id, request);

        return ResponseEntity.ok(
                BaseResponseDTO.<ApartmentResponseDTO>builder()
                        .success(true)
                        .message("Update apartment successfully")
                        .data(apartment)
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<Void>> deleteApartment(@PathVariable Long id) {

        apartmentService.deleteApartment(id);

        return ResponseEntity.ok(
                BaseResponseDTO.<Void>builder()
                        .success(true)
                        .message("Delete apartment successfully")
                        .build());
    }
}
