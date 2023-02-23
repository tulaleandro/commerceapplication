package com.app.commerce.web;

import com.app.commerce.web.model.PricesResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static com.app.commerce.service.PriceServices.PriceService;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({CommerceController.class})
public class CommerceControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    PriceService priceService;
    @Test
    void shouldReturnOKEmptyResult() throws Exception {

        Mockito.when(priceService.retrieveByBrandProductAndDateBetween(any(), any(), any())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/prices")
                        .param("brand", "ZARA")
                        .param("inputDate", "2020-06-14T11:00:00.000Z")
                        .param("productId", "35455"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

    }

    @Test
    void shouldReturnOK() throws Exception {

        PricesResponse prices = PricesResponse.builder()
                .productId(3455L)
                .startDate(OffsetDateTime.parse("2020-06-14T10:00:00.00+00:00"))
                .endDate(OffsetDateTime.parse("2020-06-15T10:00:00.00+00:00"))
                .brandId(1L)
                .priority(0L)
                .build();

        Mockito.when(priceService.retrieveByBrandProductAndDateBetween(any(), any(), any())).thenReturn(List.of(prices));

        mockMvc.perform(get("/prices")
                        .param("brand", "ZARA")
                        .param("inputDate", "2020-06-14T11:00:00.000Z")
                        .param("productId", "3455"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.[0].productId").value("3455"))
                .andExpect(jsonPath("$.[0].brandId").value("1"))
                .andExpect(jsonPath("$.[0].startDate").isNotEmpty())
                .andExpect(jsonPath("$.[0].endDate").isNotEmpty())
                .andReturn();

    }

}
