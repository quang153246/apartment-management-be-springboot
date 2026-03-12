package com.real_estate.service;

import com.real_estate.model.dto.request.CreateApartmentRequest;
import com.real_estate.model.dto.response.ApartmentResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApartmentService {
    ApartmentResponseDTO createApartment(CreateApartmentRequest request);

    Page<ApartmentResponseDTO> getApartments(Pageable pageable);

    Page<ApartmentResponseDTO> getApartmentsByBuildingId(Long buildingId, Pageable pageable);

    ApartmentResponseDTO getApartmentById(Long id);

    ApartmentResponseDTO updateApartment(Long id, CreateApartmentRequest request);

    void deleteApartment(Long id);
}
