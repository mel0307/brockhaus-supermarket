package org.brockhaus.supermarket.model;

public record ProductDto (

    String name,
    String type,
    int currentQuality,
    double currentPrice,
    String expiryDate,
    boolean shouldDispose) {

    public static ProductDto from(ProductEntity entity, double currentPrice, int currentQuality) {

        return new ProductDto(
                entity.getName(),
                entity.getType().name().toLowerCase(),
                currentQuality,
                currentPrice,
                entity.getExpiryDate() != null ? entity.getExpiryDate().toString() : null,
                currentQuality < entity.getType().getLowestQualityScore()
        );
    }
}
