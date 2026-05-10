package org.brockhaus.supermarket.model;

public enum ProductType {
    WINE(ProductCategory.BEVERAGES, 50, 0, false),
    CHEESE(ProductCategory.DAIRY_AND_EGGS, 30,0, true),
    BREAD(ProductCategory.BAKERY, 5, 0, true),;

    private final ProductCategory category;
    private final int bestQualityScore;
    private final int lowestQualityScore;
    private final boolean dailyPriceAvailable;

    ProductType(ProductCategory category, int bestQualityScore, int lowestQualityScore, boolean dailyPriceAvailable) {
        this.category = category;
        this.bestQualityScore = bestQualityScore;
        this.lowestQualityScore = lowestQualityScore;
        this.dailyPriceAvailable = dailyPriceAvailable;
    }

    public int getBestQualityScore() {
        return bestQualityScore;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public boolean isDailyPriceAvailable() {
        return dailyPriceAvailable;
    }

    public int getLowestQualityScore() {
        return lowestQualityScore;
    }
}
