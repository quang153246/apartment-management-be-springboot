package com.real_estate.repository;

import com.real_estate.model.entity.BuildingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {
    Page<BuildingEntity> findByProjectId(Long projectId, Pageable pageable);
}
