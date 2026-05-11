package org.brockhaus.supermarket.product;

import org.brockhaus.supermarket.model.Product;

import java.time.LocalDate;

public final class Bread extends Product {

    private static final int BEST_QUALITY  = 5;
    private static final int LOWEST_QUALITY = 0;

    public Bread(String name, int quality, double basePrice, LocalDate expiryDate, LocalDate insertionDate) {
        super(name, quality, basePrice, expiryDate, insertionDate);
    }

    @Override
    public int calculateQuality(LocalDate today) {
        return clampQuality(this.getBaseQualityScore() - daysBetween(this.getInsertionDate(), today));
    }

    @Override
    public int getBestQualityScore() {
        return BEST_QUALITY;
    }

    @Override
    public int getLowestQualityScore() {
        return LOWEST_QUALITY;
    }

    @Override
    protected boolean hasDailyPrice() {
        return true;
    }

    @Override
    protected boolean requiresExpiryDate() {
        return true;
    }
}
