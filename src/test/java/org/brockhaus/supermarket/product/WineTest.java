package org.brockhaus.supermarket.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

@DisplayName("Wine")
class WineTest {

    private static final LocalDate INSERTION = LocalDate.of(2024, 1, 1);

    private Wine wine(int quality) {
        return new Wine("Riesling", quality, 8.00, null, INSERTION);
    }

    @Test
    @DisplayName("Qualität steigt alle 10 Tage um 1")
    void qualityIncreasesEveryTenDays() {
        var wine = wine(10);
        assertThat(wine.calculateQuality(INSERTION.plusDays(10))).isEqualTo(11);
        assertThat(wine.calculateQuality(INSERTION.plusDays(20))).isEqualTo(12);
        assertThat(wine.calculateQuality(INSERTION.plusDays(30))).isEqualTo(13);
    }

    @Test
    @DisplayName("Qualität steigt nicht über Maximum (50)")
    void qualityDoesNotExceedMaximum() {
        var wine = wine(49);
        assertThat(wine.calculateQuality(INSERTION.plusDays(10))).isEqualTo(50);
        assertThat(wine.calculateQuality(INSERTION.plusDays(100))).isEqualTo(50);
    }

    @Test
    @DisplayName("Qualität bleibt gleich wenn weniger als 10 Tage vergangen")
    void qualityUnchangedBeforeTenDays() {
        var wine = wine(10);
        assertThat(wine.calculateQuality(INSERTION.plusDays(9))).isEqualTo(10);
    }

    @Test
    @DisplayName("Preis ändert sich nicht (kein tagesaktueller Preis)")
    void priceDoesNotChange() {
        var wine = wine(10);
        double priceDay0  = wine.calculatePrice(INSERTION);
        double priceDay30 = wine.calculatePrice(INSERTION.plusDays(30));
        assertThat(priceDay0).isEqualTo(priceDay30);
    }

    @Test
    @DisplayName("Wein verfällt nicht → isDisposable() immer false")
    void wineNeverExpires() {
        var wine = wine(10);
        assertThat(wine.isDisposable(INSERTION.plusDays(9999))).isFalse();
    }

    @Test
    @DisplayName("Qualität 0 ist gültig (nicht-negativ erlaubt)")
    void zeroQualityIsValid() {
        assertThatNoException().isThrownBy(() -> wine(0));
    }

    @Test
    @DisplayName("Negative Qualität wirft Exception")
    void negativeQualityThrows() {
        assertThatIllegalArgumentException().isThrownBy(() -> wine(-1));
    }
}