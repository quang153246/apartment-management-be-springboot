package com.real_estate.model.mapper;

import com.real_estate.model.dto.request.RegisterRequest;
import com.real_estate.model.dto.response.UserResponseDTO;
import com.real_estate.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    UserEntity toEntity(RegisterRequest request);

    UserResponseDTO toDTO(UserEntity entity);
}