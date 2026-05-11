package org.brockhaus.brockhaussupermarket;

import org.brockhaus.supermarket.SupermarketApplication;
import org.brockhaus.supermarket.product.Bread;
import org.brockhaus.supermarket.product.Cheese;
import org.brockhaus.supermarket.product.Wine;
import org.brockhaus.supermarket.repository.ProductRepository;
import org.brockhaus.supermarket.runner.SupermarketRunner;
import org.brockhaus.supermarket.service.SalesService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@SpringBootTest(classes = SupermarketApplication.class)
@DisplayName("SupermarketApplication")
class SupermarketApplicationTests {

    @Test
    @DisplayName("Spring Context lädt erfolgreich")
    void contextLoads() {
        // Spring Boot startet ohne Exception → Test grün
    }

    @Test
    @DisplayName("Alle Beans sind vorhanden")
    void beansArePresent(
            @Autowired SalesService salesService,
            @Autowired ProductRepository productRepository,
            @Autowired SupermarketRunner supermarketRunner) {

        assertThat(salesService).isNotNull();
        assertThat(productRepository).isNotNull();
        assertThat(supermarketRunner).isNotNull();
    }

    @Test
    @DisplayName("Produkte sind geladen")
    void productsAreLoaded(@Autowired SalesService salesService) {
        var products = salesService.loadAll();

        assertThat(products)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    @DisplayName("Sortiment enthält alle Produkttypen")
    void allProductTypesPresent(@Autowired SalesService salesService) {
        var products = salesService.loadAll();

        assertThat(products).hasAtLeastOneElementOfType(Wine.class);
        assertThat(products).hasAtLeastOneElementOfType(Cheese.class);
        assertThat(products).hasAtLeastOneElementOfType(Bread.class);
    }

    @Test
    @DisplayName("Runner startet ohne Exception")
    void runnerStartsWithoutException(@Autowired SupermarketRunner runner) {
        assertThatNoException().isThrownBy(() -> runner.run());
    }
}
