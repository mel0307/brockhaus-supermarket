package org.brockhaus.supermarket.service;

import org.brockhaus.supermarket.model.Product;
import org.brockhaus.supermarket.model.ProductEntity;
import org.brockhaus.supermarket.product.Bread;
import org.brockhaus.supermarket.product.Cheese;
import org.brockhaus.supermarket.product.Wine;
import org.brockhaus.supermarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SalesService {

    private final ProductRepository repository;

    public SalesService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> loadAll() {
        return repository.findAll()
                .stream()
                .map(this::toProduct)
                .toList();
    }

    private Product toProduct(ProductEntity e) {
        return switch (e.getType()) {
            case WINE   -> new Wine(e.getName(), e.getQuality(), e.getBasePrice(), e.getExpiryDate(), e.getInsertionDate());
            case CHEESE -> new Cheese(e.getName(), e.getQuality(), e.getBasePrice(),
                    e.getExpiryDate(), e.getInsertionDate());
            case BREAD  -> new Bread(e.getName(), e.getQuality(), e.getBasePrice(),
                    e.getExpiryDate(), e.getInsertionDate());
        };
    }
}
