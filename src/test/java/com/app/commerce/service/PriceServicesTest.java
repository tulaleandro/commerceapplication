package com.app.commerce.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.time.OffsetDateTime;

import static com.app.commerce.autoconfigure.PricesAutoconfiguration.*;
import static com.app.commerce.service.PriceServices.*;

@ImportAutoConfiguration({PriceServiceAutoConfiguration.class})
@SpringBootTest
@TestExecutionListeners({
        TransactionalTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@Transactional
@TestPropertySource(locations="/application-test.properties")
@DatabaseSetup("/global-data.xml")
public class PriceServicesTest {
    @Autowired
    PriceService priceService;
    @Test
    void shouldFindPriceRecordForGivenId() {

        var record = priceService.retrieveByBrandProductAndDateBetween(OffsetDateTime.parse("2020-06-14T10:00:00.00+00:00"), "ZARA", 35455L);
        Assertions.assertFalse(record.isEmpty());
    }
}
