package org.brockhaus.supermarket.service;


import org.brockhaus.supermarket.model.ProductDto;
import org.brockhaus.supermarket.model.ProductEntity;
import org.brockhaus.supermarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class SalesService {

    private final ProductRepository productRepository;
    private final ProductPricingAndQualityService pricingAndQualityService;

    public SalesService(ProductRepository productRepository, ProductPricingAndQualityService pricingAndQualityService) {
        this.productRepository = productRepository;
        this.pricingAndQualityService = pricingAndQualityService;
    }

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public List<ProductDto> processAndGetAllProducts(LocalDate today) {
//        List<ProductEntity> products = findAllProductsOrderByCategory();
        List<ProductEntity> products = productRepository.findAllOrderByType();
        return products.stream().map(productEntity -> {
                    int currentQuality = pricingAndQualityService.calculateQuality(productEntity, today);

                    return ProductDto.from(productEntity,
                            pricingAndQualityService.calculatePrice(productEntity, currentQuality), currentQuality);
                })
                .toList();
    }

    private List<ProductEntity> findAllProductsOrderByCategory() {
        return productRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(p -> p.getType().getCategory()))
                .toList();
    }
}
