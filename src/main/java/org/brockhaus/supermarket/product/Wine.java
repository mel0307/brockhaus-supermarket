package org.brockhaus.supermarket.product;

import org.brockhaus.supermarket.model.Product;

import java.time.LocalDate;

public final class Wine extends Product {

    private static final int BEST_QUALITY  = 50;
    private static final int LOWEST_QUALITY = 0;

    public Wine(String name, int quality, double basePrice, LocalDate expiryDate, LocalDate insertionDate) {
        super(name, quality, basePrice, expiryDate, insertionDate);
    }

    @Override
    public int getBestQualityScore() { return BEST_QUALITY; }

    @Override
    public int getLowestQualityScore() { return LOWEST_QUALITY; }

    @Override
    protected boolean hasDailyPrice() {
        return false;
    }

    @Override
    protected boolean requiresExpiryDate() {
        return false;
    }

    @Override
    public int calculateQuality(LocalDate today) {
        return clampQuality(Math.min(getBestQualityScore(), this.getBaseQualityScore() + (daysBetween(this.getInsertionDate(), today) / 10)));
    }
}
