package com.app.commerce.data.jpa.dao;

import com.app.commerce.data.jpa.entity.PricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<PricesEntity, Long> {
    @Query("select p from prices p inner join fetch brands b where p.startDate <= :inputDate and p.endDate >= :inputDate and p.brand.name = :brandName and p.productId = :productId")
    List<PricesEntity> retrieveByPrice(@Param("inputDate")OffsetDateTime inputDate,
                                       @Param("brandName") String brandName,
                                       @Param("productId") Long productId);

}
