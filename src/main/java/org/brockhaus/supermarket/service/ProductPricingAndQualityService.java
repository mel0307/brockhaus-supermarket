package org.brockhaus.supermarket.service;

import org.brockhaus.supermarket.model.ProductEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.brockhaus.supermarket.model.ProductType.WINE;

@Service
public class ProductPricingAndQualityService {

    public int calculateQuality(ProductEntity product, LocalDate today) {
        return switch (product.getType()) {
            case WINE ->
                    Math.min(WINE.getBestQualityScore(), product.getQuality() + (daysBetween(product.getInsertionDate(), today) / 10));
            case CHEESE, BREAD -> product.getQuality() - daysBetween(product.getInsertionDate(), today);
            default -> product.getQuality();
        };
    }

    public double calculatePrice(ProductEntity product, int currentQuality) {
        if (product.getType().isDailyPriceAvailable()) {
            return product.getBasePrice() + (0.1 * currentQuality);
        } else {
            return product.getBasePrice();
        }
    }


    private int daysBetween(LocalDate startDate, LocalDate endDate) {
        return (int) Math.floor(ChronoUnit.DAYS.between(startDate, endDate));
    }
}
