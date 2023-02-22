package com.app.commerce.data.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;
import static lombok.AccessLevel.PUBLIC;

@Entity(name = "brands")
@Table(name = "brands")
@Builder(toBuilder = true)
@AllArgsConstructor(access = PUBLIC)
@NoArgsConstructor(access = PROTECTED)
@Setter(value = PROTECTED)
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BrandEntity {

    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    Long id;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private String name;
}
