package com.app.commerce.web;

import com.app.commerce.web.model.PricesResponse;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

import static com.app.commerce.service.PriceServices.PriceService;

@RestController
@RequestMapping("prices")
@Value
@RequiredArgsConstructor
public class CommerceController {

    PriceService priceService;

    @GetMapping
    public ResponseEntity<List<PricesResponse>> getPrices(@RequestParam("inputDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final OffsetDateTime inputDate,
                                                          @RequestParam("brand") String brand,
                                                          @RequestParam("productId") Long productId) {


        return ResponseEntity.ok(priceService.retrieveByBrandProductAndDateBetween(inputDate, brand, productId));
    }

}
