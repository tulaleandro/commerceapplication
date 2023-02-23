package com.app.commerce.web.model;

import lombok.*;

import java.time.OffsetDateTime;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PUBLIC;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = PUBLIC)
@NoArgsConstructor(access = PACKAGE)
@Setter(PACKAGE)
public class PricesResponse {

    private Long productId;
    private Long brandId;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private Long priority;
    private Float price;
}
