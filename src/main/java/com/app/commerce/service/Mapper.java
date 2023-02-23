package com.app.commerce.service;

import com.app.commerce.data.jpa.entity.PricesEntity;
import com.app.commerce.web.model.PricesResponse;
import lombok.Value;
import lombok.experimental.Accessors;
import lombok.experimental.NonFinal;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Mapper {

    @Value
    @NonFinal
    @Accessors(fluent = true)
    public static class CustomMapper {

        public PricesResponse mapToResponse(PricesEntity pricesEntity, Float totalPrice) {
            return PricesResponse.builder()
                    .brandId(pricesEntity.getBrand().getId())
                    .endDate(pricesEntity.getEndDate())
                    .startDate(pricesEntity.getEndDate())
                    .productId(pricesEntity.getProductId())
                    .priority(pricesEntity.getPriority())
                    .price(totalPrice)
                    .build();
        }
    }


}
