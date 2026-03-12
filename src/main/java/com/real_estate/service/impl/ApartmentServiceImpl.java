package com.real_estate.service.impl;

import com.real_estate.exception.ResourceNotFoundException;
import com.real_estate.model.dto.request.CreateApartmentRequest;
import com.real_estate.model.dto.response.ApartmentResponseDTO;
import com.real_estate.model.entity.ApartmentEntity;
import com.real_estate.model.entity.BuildingEntity;
import com.real_estate.model.mapper.ApartmentMapper;
import com.real_estate.repository.ApartmentRepository;
import com.real_estate.repository.BuildingRepository;
import com.real_estate.service.ApartmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final BuildingRepository buildingRepository;
    private final ApartmentMapper apartmentMapper;

    @Override
    public ApartmentResponseDTO createApartment(CreateApartmentRequest request) {
        BuildingEntity building = buildingRepository.findById(request.getBuildingId())
                .orElseThrow(() -> new ResourceNotFoundException("Building not found"));

        ApartmentEntity apartment = ApartmentEntity.builder()
                .building(building)
                .code(request.getCode())
                .floor(request.getFloor())
                .area(request.getArea())
                .bedrooms(request.getBedrooms())
                .bathrooms(request.getBathrooms())
                .direction(request.getDirection())
                .view(request.getView())
                .status(request.getStatus())
                .type(request.getType())
                .priceSale(request.getPriceSale())
                .priceRent(request.getPriceRent())
                .description(request.getDescription())
                .build();

        ApartmentEntity saved = apartmentRepository.save(apartment);


        return apartmentMapper.toDTO(saved);
    }

    @Override
    public Page<ApartmentResponseDTO> getApartments(Pageable pageable) {
        return apartmentRepository.findAll(pageable)
                .map(apartmentMapper::toDTO);
    }

    @Override
    public Page<ApartmentResponseDTO> getApartmentsByBuildingId(Long buildingId, Pageable pageable) {
        BuildingEntity building = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new ResourceNotFoundException("Building not found"));

        return apartmentRepository
                .findByBuildingId(buildingId, pageable)
                .map(apartmentMapper::toDTO);
    }

    @Override
    public ApartmentResponseDTO getApartmentById(Long id) {
        ApartmentEntity apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Apartment not found"));

        return apartmentMapper.toDTO(apartment);
    }

    @Override
    public ApartmentResponseDTO updateApartment(Long id, CreateApartmentRequest request) {
        ApartmentEntity apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Apartment not found"));


        apartmentMapper.updateApartmentFromRequest(request, apartment);
        return apartmentMapper.toDTO(apartmentRepository.save(apartment));
    }

    @Override
    public void deleteApartment(Long id) {
        if (!apartmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Apartment not found");
        }

        apartmentRepository.deleteById(id);
    }
}
