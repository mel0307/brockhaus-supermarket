package org.brockhaus.supermarket.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public abstract class Product {

    private final String name;
    private final double basePrice;
    private final int baseQualityScore;

    private final LocalDate expiryDate;
    private final LocalDate insertionDate;


    protected Product(String name, int baseQualityScore, double basePrice, LocalDate expiryDate,
                      LocalDate insertionDate) {
        this.name          = Objects.requireNonNull(name, "name must not be null");
        this.basePrice     = basePrice;
        this.expiryDate    = requiresExpiryDate() ? Objects.requireNonNull(expiryDate, "expiryDate must not be null") : expiryDate;
        this.insertionDate = Objects.requireNonNull(insertionDate, "insertionDate must not be null");

        // quality NACH den anderen Feldern – validateQuality() kann Getters der Subklasse nutzen
        validateQuality(baseQualityScore);
        this.baseQualityScore = baseQualityScore;
    }

    public abstract int calculateQuality(LocalDate today);
    protected abstract int getBestQualityScore();
    protected abstract int getLowestQualityScore();
    protected abstract boolean hasDailyPrice();
    protected abstract boolean requiresExpiryDate();

    public boolean isDisposable(LocalDate today) {
        return calculateQuality(today) <= getLowestQualityScore()
                || (getExpiryDate() != null && !today.isBefore(getExpiryDate()));
    }

    public double calculatePrice(LocalDate today) {
        return hasDailyPrice()
                ? basePrice + (0.1 * calculateQuality(today))
                : basePrice;
    }

    protected int daysBetween(LocalDate startDate, LocalDate endDate) {
        return (int) Math.floor(ChronoUnit.DAYS.between(startDate, endDate));
    }

    protected final void validateQuality(int value) {
        if (value < getLowestQualityScore() || value > getBestQualityScore()) {
            throw new IllegalArgumentException(
                    "Quality %d is out of range [%d, %d] for %s".formatted(
                            value, getLowestQualityScore(), getBestQualityScore(),
                            getClass().getSimpleName())
            );
        }
    }

    protected int clampQuality(int value) {
        return Math.max(getLowestQualityScore(), Math.min(getBestQualityScore(), value));
    }

    public double getBasePrice() {
        return basePrice;
    }

    public int getBaseQualityScore() {
        return baseQualityScore;
    }

    public LocalDate getInsertionDate() {
        return insertionDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public String getName() {
        return name;
    }
}
