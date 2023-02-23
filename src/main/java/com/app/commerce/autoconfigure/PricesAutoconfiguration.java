package com.app.commerce.autoconfigure;

import com.app.commerce.data.jpa.dao.PriceRepository;
import lombok.Value;
import lombok.experimental.Accessors;
import lombok.experimental.NonFinal;
import lombok.experimental.UtilityClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.app.commerce.service.Mapper.*;
import static com.app.commerce.service.PriceServices.PriceService;
import static com.app.commerce.service.PriceServices.PriceServiceImpl;

@UtilityClass
public class PricesAutoconfiguration {

    @Configuration
    @Value
    @NonFinal
    @Accessors(fluent = true)
    public static class PriceServiceAutoConfiguration {
        PriceRepository priceRepository;
        @Bean
        @ConditionalOnMissingBean
        public CustomMapper customMapper() {
            return new CustomMapper();
        }
        @Bean
        @ConditionalOnMissingBean
        public PriceService priceServices() {
            return new PriceServiceImpl(priceRepository(), customMapper());
        }

    }
}
