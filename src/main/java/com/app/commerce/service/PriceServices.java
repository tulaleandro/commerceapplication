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

import static com.app.commerce.service.Mapper.*;

@UtilityClass
public class PriceServices {

    public interface PriceService {

        List<PricesResponse> retrieveByBrandProductAndDateBetween(OffsetDateTime inputDate, String brandName, Long productId);

        Map<Float, List<PricesEntity>> getGroupedRecordByPrice(OffsetDateTime inputDate, String brandName, Long productId);
    }

    @Value
    @NonFinal
    @Accessors(fluent = true)
    public static class PriceServiceImpl implements PriceService {
        PriceRepository priceRepository;

        CustomMapper customMapper;

        @Override
        public List<PricesResponse> retrieveByBrandProductAndDateBetween(OffsetDateTime inputDate, String brandName, Long productId) {

            List<PricesEntity> collect = this.getGroupedRecordByPrice(inputDate, brandName, productId)
                    .values()
                    .stream()
                    .flatMap(Collection::stream)
                    .max(Comparator.comparing(PricesEntity::getPriority))
                    .stream()
                    .toList();
            double sum = collect.stream().mapToDouble(PricesEntity::getPrice).sum();

            return collect.stream().map(it -> customMapper().mapToResponse(it, (float) sum)).collect(Collectors.toList());
        }

        @Override
        public Map<Float, List<PricesEntity>> getGroupedRecordByPrice(OffsetDateTime inputDate, String brandName, Long productId) {
            Map<Float, List<PricesEntity>> collect = priceRepository()
                    .retrieveByPrice(inputDate, brandName, productId)
                    .stream()
                    .collect(Collectors.groupingBy(PricesEntity::getPrice));
            return collect;
        }
    }


}
