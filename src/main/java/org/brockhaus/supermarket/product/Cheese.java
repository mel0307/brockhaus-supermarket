package org.brockhaus.supermarket.product;

import org.brockhaus.supermarket.model.Product;

import java.time.LocalDate;

public final class Cheese extends Product {

    private static final int BEST_QUALITY  = 50;
    private static final int LOWEST_QUALITY = 30;

    public Cheese(String name, int quality, double basePrice, LocalDate expiryDate, LocalDate insertionDate) {
        super(name, quality, basePrice, expiryDate, insertionDate);

        long daysUntilExpiry =daysBetween(insertionDate, expiryDate);
        if (daysUntilExpiry < 50 || daysUntilExpiry > 100) {
            throw new IllegalArgumentException(
                    "Cheese expiry must be 50–100 days from insertion, got: " + daysUntilExpiry);
        }
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
