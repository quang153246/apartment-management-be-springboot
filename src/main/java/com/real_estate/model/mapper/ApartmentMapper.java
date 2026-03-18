package com.real_estate.model.mapper;

import com.real_estate.model.dto.request.CreateApartmentRequest;
import com.real_estate.model.dto.response.ApartmentResponseDTO;
import com.real_estate.model.entity.ApartmentEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ApartmentMapper {

    @Mapping(target = "building", ignore = true)
    ApartmentEntity toEntity(CreateApartmentRequest request);

    ApartmentResponseDTO toDTO(ApartmentEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "building", ignore = true)
    void updateApartmentFromRequest(
            CreateApartmentRequest request, @MappingTarget ApartmentEntity entity);
}
