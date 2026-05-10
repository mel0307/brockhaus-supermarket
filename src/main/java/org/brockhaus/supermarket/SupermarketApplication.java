package org.brockhaus.supermarket;

import org.brockhaus.supermarket.model.ProductDto;
import org.brockhaus.supermarket.service.SalesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
public class SupermarketApplication implements CommandLineRunner {

    private final SalesService salesService;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int SIMULATION_DAYS = 30;

    public SupermarketApplication(SalesService salesService) {
        this.salesService = salesService;
    }


    public static void main(String[] args) {
        SpringApplication.run(SupermarketApplication.class, args);
    }


    @Override
    public void run(String... args) {
        LocalDate today = LocalDate.now();

        System.out.println("\n╔══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║              SUPERDUPERMARKT – STARTWERTE                            ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
        printProducts(salesService.processAndGetAllProducts(today));

        for (int day = 1; day <= 10; day++) {
            LocalDate currentDay = today.plusDays(day);

            System.out.printf("%n╔═══════════════════════════════════════════════════════════════════════════════════════╗%n");
            System.out.printf("║ TAG %2d – %-77s║%n", day, currentDay.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════╝");

            printProducts(salesService.processAndGetAllProducts(currentDay));
        }
    }

    private void printProducts(List<ProductDto> products) {
        printHeader();
        products.forEach(this::printRow);
        printFooter();
    }

    private void printHeader() {
        System.out.println("├─────────────────────────┬──────────┬─────────┬────────────┬──────────────┬────────────┤");
        System.out.println("│ Name                    │ Typ      │ Qualit. │ Preis      │ Verfallsdat. │ Entsorgen? │");
        System.out.println("├─────────────────────────┼──────────┼─────────┼────────────┼──────────────┼────────────┤");
    }

    private void printRow(ProductDto dto) {
        String dispose = dto.shouldDispose() ? "⚠ JA" : "✓ Nein";
        String expiry = dto.expiryDate() != null ? dto.expiryDate() : "–";
        System.out.printf("│ %-23s │ %-8s │ %7d │ %8.2f € │ %-12s │ %-10s │%n",
                dto.name(), dto.type(), dto.currentQuality(), dto.currentPrice(), expiry, dispose);
    }

    private void printFooter() {
        System.out.println("└─────────────────────────┴──────────┴─────────┴────────────┴──────────────┴────────────┘");
    }
}
