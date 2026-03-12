package com.real_estate.model.mapper;

import com.real_estate.model.dto.request.CreateApartmentRequest;
import com.real_estate.model.dto.response.ApartmentResponseDTO;
import com.real_estate.model.entity.ApartmentEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ApartmentMapper {
    ApartmentEntity toEntity(CreateApartmentRequest request);
    ApartmentResponseDTO toDTO(ApartmentEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateApartmentFromRequest(CreateApartmentRequest request,
                                    @MappingTarget ApartmentEntity entity);
}
