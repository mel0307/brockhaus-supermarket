package org.brockhaus.supermarket.runner;

import org.brockhaus.supermarket.model.Product;
import org.brockhaus.supermarket.service.SalesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SupermarketRunner implements CommandLineRunner {

    private static final int DAYS_TO_SIMULATE = 30;
    private static final String SEPARATOR = "-".repeat(65);

    private final SalesService salesService;

    public SupermarketRunner(SalesService salesService) {
        this.salesService = salesService;
    }

    @Override
    public void run(String... args) {
        var today = LocalDate.now();

        printInitialStock(today);

        for (int day = 1; day <= DAYS_TO_SIMULATE; day++) {
            printDailyOverview(today.plusDays(day), day);
        }
    }

    private void printInitialStock(LocalDate today) {
        System.out.println(SEPARATOR);
        System.out.println("  SUPERDUPER MARKET – Initial Stock (" + today + ")");
        System.out.println(SEPARATOR);
        salesService.loadAll().forEach(p -> printProduct(p, today));
        System.out.println(SEPARATOR);
    }

    private void printDailyOverview(LocalDate date, int day) {
        System.out.printf("%n  Day +%-3d (%s)%n", day, date);
        System.out.println(SEPARATOR);
        salesService.loadAll().forEach(p -> printProduct(p, date));
        System.out.println(SEPARATOR);
    }

    private void printProduct(Product p, LocalDate today) {
        System.out.printf("  %-15s | Price: %5.2f€ | Quality: %3d | %s%n",
                p.getName(),
                p.calculatePrice(today),
                p.calculateQuality(today),
                p.isDisposable(today) ? "⚠  DISPOSE" : "✓  OK"
        );
    }
}