package com.real_estate.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "projects")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String developer;

    private String investor;

    private String description;

    private LocalDate startDate;

    private String status;

    private String thumbnail;
}
