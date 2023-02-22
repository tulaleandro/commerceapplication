package com.app.commerce;

import com.app.commerce.data.jpa.dao.BrandRepository;
import com.app.commerce.data.jpa.dao.PriceRepository;
import com.app.commerce.data.jpa.entity.BrandEntity;
import com.app.commerce.data.jpa.entity.Currency;
import com.app.commerce.data.jpa.entity.PricesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@SpringBootApplication
public class CommerceApplication implements CommandLineRunner {

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private PriceRepository priceRepository;
	public static void main(String[] args) {
		SpringApplication.run(CommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		BrandEntity brand = BrandEntity.builder().id(1L).name("ZARA").build();
		var persistedBrand = brandRepository.save(brand);

		PricesEntity price = PricesEntity.builder()
				.price(35.50F)
				.productId(35455L)
				.startDate(OffsetDateTime.parse("2020-06-14T00:00:00.000Z"))
				.endDate(OffsetDateTime.parse("2020-06-14T23:59:59.000Z"))
				.brand(persistedBrand)
				.currency(Currency.EUR)
				.priority(1L)
				.build();

		PricesEntity price2 = PricesEntity.builder()
				.price(35.50F)
				.productId(35455L)
				.startDate(OffsetDateTime.parse("2020-06-14T00:00:00.000Z"))
				.endDate(OffsetDateTime.parse("2020-06-14T23:59:59.000Z"))
				.brand(persistedBrand)
				.currency(Currency.EUR)
				.priority(0L)
				.build();
		priceRepository.save(price);
		priceRepository.save(price2);


	}
}
