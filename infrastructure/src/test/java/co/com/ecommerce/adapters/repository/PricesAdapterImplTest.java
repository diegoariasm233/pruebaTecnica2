package co.com.ecommerce.adapters.repository;

import co.com.ecommerce.model.Prices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ActiveProfiles("test")
class PricesAdapterImplTest {

    @Autowired
    private PricesAdapterImpl pricesAdapterImpl;

    @Test
    void testFindPriceByDateAndProductIdAndBrandId_ReturnsPrice() {
        Optional<Prices> result = pricesAdapterImpl
                .findPriceByDateAndProductIdAndBrandId(LocalDateTime
                                .of(2020, 6, 14, 16, 0 , 0),
                        35455L, 1L);

        assertTrue(result.isPresent());
        assertEquals(35455L, result.get().getProductId());
        assertEquals(BigDecimal.valueOf(25.45).setScale(2, RoundingMode.HALF_UP), result.get().getPrice());
    }


    @Test
    void testFindPriceByDateAndProductIdAndBrandId_ReturnsEmpty_WhenNoPriceFound() {
        Optional<Prices> result = pricesAdapterImpl
                .findPriceByDateAndProductIdAndBrandId(LocalDateTime
                                .of(2021, 6, 14, 16, 0 , 0),
                        25L, 1L);
        assertFalse(result.isPresent());
    }
}