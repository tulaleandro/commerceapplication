package com.app.commerce.data.jpa.dao;

import com.app.commerce.data.jpa.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    Optional<BrandEntity> findByNameIgnoreCase(String name);

}
