package com.real_estate.model.dto.response;

import com.real_estate.model.enums.ApartmentStatus;
import com.real_estate.model.enums.ApartmentType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApartmentResponseDTO {
    private Long id;

    private Long buildingId;

    private String buildingName;

    private String code;

    private Integer floor;

    private Double area;

    private Integer bedrooms;

    private Integer bathrooms;

    private String direction;

    private String view;

    private ApartmentStatus status;

    private ApartmentType type;

    private BigDecimal priceSale;

    private BigDecimal priceRent;

    private String description;

    private LocalDateTime createdAt;
}
