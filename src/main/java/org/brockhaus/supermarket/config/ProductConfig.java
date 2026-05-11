package org.brockhaus.supermarket.config;

import org.brockhaus.supermarket.model.Product;
import org.brockhaus.supermarket.product.Bread;
import org.brockhaus.supermarket.product.Cheese;
import org.brockhaus.supermarket.product.Wine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class ProductConfig {

    @Bean
    public List<Product> products() {
        var today = LocalDate.now();
        return List.of(
                new Cheese("Gouda",     50, 2.00, today.plusDays(70), today),
                new Wine("Asbach",    10, 8.00, today.plusYears(100), today),
                new Bread("Vollkorn",   4, 1.50, today.plusDays(3),  today)
        );
    }
}
