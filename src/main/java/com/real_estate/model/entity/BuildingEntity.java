package com.real_estate.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "buildings")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuildingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private Integer floors;

    @Column(name = "total_apartments")
    private Integer totalApartments;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String status;
}
