package com.app.commerce.data.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.OffsetDateTime;

import static lombok.AccessLevel.PROTECTED;
import static lombok.AccessLevel.PUBLIC;

@Entity(name = "prices")
@Table(name = "prices")
@Builder(toBuilder = true)
@AllArgsConstructor(access = PUBLIC)
@NoArgsConstructor(access = PROTECTED)
@Setter(value = PROTECTED)
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PricesEntity {

    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    Long priceListId;

    @Column(nullable = false)
    private OffsetDateTime startDate;

    @Column(nullable = false)
    private OffsetDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long priority;

    @Column(nullable = false)
    private Float price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "brand_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private BrandEntity brand;

}
