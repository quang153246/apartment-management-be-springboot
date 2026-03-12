package com.real_estate.repository;

import com.real_estate.model.entity.ApartmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<ApartmentEntity, Long> {
    Page<ApartmentEntity> findByBuildingId(Long buildingId, Pageable pageable);
}
