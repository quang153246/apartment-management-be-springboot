package com.real_estate.model.dto.request;

import com.real_estate.model.enums.ApartmentStatus;
import com.real_estate.model.enums.ApartmentType;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateApartmentRequest {
    @NotNull(message = "Building id is required")
    private Long buildingId;

    @NotNull(message = "Code is required")
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
}
