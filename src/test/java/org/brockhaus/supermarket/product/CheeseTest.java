package org.brockhaus.supermarket.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("Cheese")
class CheeseTest {

    private static final LocalDate INSERTION = LocalDate.of(2024, 1, 1);

    private Cheese cheese(int quality, LocalDate expiry) {
        return new Cheese("Gouda", quality, 2.00, expiry, INSERTION);
    }

    private Cheese defaultCheese(int quality) {
        return cheese(quality, INSERTION.plusDays(70)); // gültiges Verfallsdatum
    }

    @Test
    @DisplayName("Qualität sinkt täglich um 1")
    void qualityDecreasesDaily() {
        var cheese = defaultCheese(50);
        assertThat(cheese.calculateQuality(INSERTION.plusDays(1))).isEqualTo(49);
        assertThat(cheese.calculateQuality(INSERTION.plusDays(5))).isEqualTo(45);
    }

    @Test
    @DisplayName("Preis ist tagesaktuell: Grundpreis + 0.10 * Qualität")
    void priceIsDynamic() {
        var cheese = defaultCheese(50);
        // Qualität nach 5 Tagen = 45 → Preis = 2.00 + 0.10 * 45 = 6.50
        assertThat(cheese.calculatePrice(INSERTION.plusDays(5))).isEqualTo(6.50);
    }

    @Test
    @DisplayName("Qualität unter 30 → isDisposable() true")
    void disposableWhenQualityBelowMinimum() {
        var cheese = defaultCheese(30);
        // Nach 1 Tag: Qualität = 31 → entsorgen
        assertThat(cheese.isDisposable(INSERTION.plusDays(1))).isTrue();
    }

    @Test
    @DisplayName("Qualität genau 30 → isDisposable() false")
    void notDisposableAtMinimumQuality() {
        var cheese = defaultCheese(31);
        assertThat(cheese.isDisposable(INSERTION)).isFalse();
    }

    @Test
    @DisplayName("Verfallsdatum erreicht → isDisposable() true")
    void disposableOnExpiryDate() {
        var cheese = defaultCheese(30);
        assertThat(cheese.isDisposable(INSERTION.plusDays(70))).isTrue();
    }

    @Test
    @DisplayName("Verfallsdatum unter 50 Tagen wirft Exception")
    void expiryTooSoonThrows() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> cheese(50, INSERTION.plusDays(49)));
    }

    @Test
    @DisplayName("Verfallsdatum über 100 Tagen wirft Exception")
    void expiryTooLateThrows() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> cheese(50, INSERTION.plusDays(101)));
    }

    @Test
    @DisplayName("Qualität unter 30 beim Einräumen wirft Exception")
    void qualityBelowMinimumThrows() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> defaultCheese(29));
    }
}