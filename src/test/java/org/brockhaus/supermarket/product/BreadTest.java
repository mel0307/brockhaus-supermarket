package org.brockhaus.supermarket.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("Bread")
class BreadTest {

    private static final LocalDate INSERTION = LocalDate.of(2024, 1, 1);

    private Bread bread(int quality) {
        return new Bread("Sourdough", quality, 1.50,
                INSERTION.plusDays(3), INSERTION);
    }

    @Test
    @DisplayName("Qualität sinkt täglich um 1")
    void qualityDecreasesDaily() {
        var bread = bread(4);
        assertThat(bread.calculateQuality(INSERTION.plusDays(1))).isEqualTo(3);
        assertThat(bread.calculateQuality(INSERTION.plusDays(4))).isEqualTo(0);
    }

    @Test
    @DisplayName("Qualität sinkt nicht unter 0")
    void qualityDoesNotGoBelowZero() {
        var bread = bread(2);
        assertThat(bread.calculateQuality(INSERTION.plusDays(999))).isEqualTo(0);
    }

    @Test
    @DisplayName("Qualität über Maximum wirft Exception")
    void qualityAboveMaximumThrows() {
        assertThatIllegalArgumentException().isThrownBy(() -> bread(6));
    }
}
