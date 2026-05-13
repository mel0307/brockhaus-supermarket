package org.brockhaus.supermarket.model;

public enum ProductType {
    WINE(ProductCategory.BEVERAGES),
    CHEESE(ProductCategory.DAIRY_AND_EGGS),
    BREAD(ProductCategory.BAKERY),;

    private final ProductCategory category;

    ProductType(ProductCategory category) {
        this.category = category;
    }

    public ProductCategory getCategory() {
        return this.category;
    }
}
