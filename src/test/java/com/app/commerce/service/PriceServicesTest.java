package com.app.commerce.service;

import com.app.commerce.data.jpa.entity.PricesEntity;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

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
    @DisplayName("Scenario duplicate records by price")
    void testDuplicateRecordShouldGetTheOneOfMorePriority() {

        // Found two records by price. Map should have one key and two records as value
        var groupedRecordsFound = priceService.getGroupedRecordByPrice(OffsetDateTime.parse("2020-06-14T10:00:00.00+00:00"), "ZARA", 35455L);
        List<PricesEntity> allContent = groupedRecordsFound.values().stream().flatMap(Collection::stream).toList();
        Assertions.assertEquals(2, allContent.size());

        // should
        var record = priceService.retrieveByBrandProductAndDateBetween(OffsetDateTime.parse("2020-06-14T10:00:00.00+00:00"), "ZARA", 35455L);
        Assertions.assertFalse(record.isEmpty());
        Assertions.assertEquals(1L, record.get(0).getPriority());
    }

    @Test
    @DisplayName("Scenario n2")
    void testShouldReturnOneRecordScenario2() {

        var record = priceService.retrieveByBrandProductAndDateBetween(OffsetDateTime.parse("2020-06-14T16:00:00.00+00:00"), "ZARA", 35455L);
        Assertions.assertEquals(1, record.size());
    }

    @Test
    @DisplayName("Scenario n3")
    void testShouldReturnOneRecordScenario3() {

        var record = priceService.retrieveByBrandProductAndDateBetween(OffsetDateTime.parse("2020-06-14T21:00:00.00+00:00"), "ZARA", 35455L);
        Assertions.assertEquals(1, record.size());
    }

    @Test
    @DisplayName("Scenario n4")
    void testShouldReturnOneRecordScenario4() {

        var record = priceService.retrieveByBrandProductAndDateBetween(OffsetDateTime.parse("2020-06-15T10:00:00.00+00:00"), "ZARA", 35455L);
        Assertions.assertEquals(1, record.size());
    }

    @Test
    @DisplayName("Scenario n5")
    void testShouldReturnOneRecordScenario5() {

        var record = priceService.retrieveByBrandProductAndDateBetween(OffsetDateTime.parse("2020-06-16T10:00:00.00+00:00"), "ZARA", 35455L);
        Assertions.assertEquals(1, record.size());
    }

}
