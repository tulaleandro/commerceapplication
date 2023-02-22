package com.app.commerce.service;

import com.app.commerce.data.jpa.dao.PriceRepository;
import com.app.commerce.data.jpa.entity.PricesEntity;
import com.app.commerce.web.model.PricesResponse;
import lombok.Value;
import lombok.experimental.Accessors;
import lombok.experimental.NonFinal;
import lombok.experimental.UtilityClass;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@UtilityClass
public class PriceServices {

    public interface PriceService {

        List<PricesResponse> retrieveByBrandProductAndDateBetween(OffsetDateTime inputDate, String brandName, Long productId);
    }

    @Value
    @NonFinal
    @Accessors(fluent = true)
    public static class PriceServiceImpl implements PriceService {
        PriceRepository priceRepository;

        @Override
        public List<PricesResponse> retrieveByBrandProductAndDateBetween(OffsetDateTime inputDate, String brandName, Long productId) {

            Map<Float, List<PricesEntity>> groupedRecordsByPrice = priceRepository().retrieveByPrice(inputDate, brandName, productId).stream()
                    .collect(Collectors.groupingBy(PricesEntity::getPrice));

            return groupedRecordsByPrice
                    .values()
                    .stream()
                    .flatMap(Collection::stream)
                    .max(Comparator.comparing(PricesEntity::getPriority))
                    .stream().map(this::mapToResponse).collect(Collectors.toList());

        }

        private PricesResponse mapToResponse(PricesEntity pricesEntity) {
            return PricesResponse.builder()
                    .brandId(pricesEntity.getBrand().getId())
                    .endDate(pricesEntity.getEndDate())
                    .startDate(pricesEntity.getEndDate())
                    .productId(pricesEntity.getProductId())
                    .priority(pricesEntity.getPriority())
                    .build();
        }
    }


}
