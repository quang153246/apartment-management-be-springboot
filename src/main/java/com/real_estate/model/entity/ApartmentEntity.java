package com.real_estate.model.entity;

import com.real_estate.model.enums.ApartmentStatus;
import com.real_estate.model.enums.ApartmentType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(
        name = "apartments",
        indexes = {
            @Index(name = "idx_building_id", columnList = "building_id"),
            @Index(name = "idx_status", columnList = "status"),
            @Index(name = "idx_type", columnList = "type")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApartmentEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    private BuildingEntity building;

    @Column(nullable = false, unique = true)
    private String code;

    private Integer floor;

    @Column(name = "area")
    private Double area;

    private Integer bedrooms;

    private Integer bathrooms;

    private String direction;

    private String view;

    @Enumerated(EnumType.STRING)
    private ApartmentStatus status;

    @Enumerated(EnumType.STRING)
    private ApartmentType type;

    @Column(name = "price_sale", precision = 15, scale = 2)
    private BigDecimal priceSale;

    @Column(name = "price_rent", precision = 15, scale = 2)
    private BigDecimal priceRent;

    @Column(columnDefinition = "TEXT")
    private String description;
}
