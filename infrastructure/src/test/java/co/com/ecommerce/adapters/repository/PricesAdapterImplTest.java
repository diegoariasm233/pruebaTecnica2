package co.com.ecommerce.adapters.repository;

import co.com.ecommerce.model.Prices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ActiveProfiles("test")
class PricesAdapterImplTest {

    @Autowired
    private PricesAdapterImpl pricesAdapterImpl;

    @Test
    void testFindPriceByDateAndProductIdAndBrandId_ReturnsPrice() {
        List<Prices> result = pricesAdapterImpl
                .findPriceByProductIdAndBrandId(35455L, 1L);
        assertFalse(result.isEmpty());
        assertTrue(result.stream()
                .allMatch(price ->
                        price.getProductId().equals(35455L) && price.getBrand().getBrandId().equals(1L)
                )
        );

    }


    @Test
    void testFindPriceByDateAndProductIdAndBrandId_ReturnsEmpty_WhenNoPriceFound() {
        List<Prices> result = pricesAdapterImpl
                .findPriceByProductIdAndBrandId(25L, 1L);
        assertTrue(result.isEmpty());
    }
}