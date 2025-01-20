package co.com.ecommerce.service;

import co.com.ecommerce.external.PricesAdapterInterface;
import co.com.ecommerce.model.Brand;
import co.com.ecommerce.model.Prices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class ProductPriceServiceTest {

    @Mock
    private PricesAdapterInterface pricesAdapterInterface;

    @InjectMocks
    private ProductPriceService priceUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPriceForProduct_PriceFound() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 2L;

        Brand brand = new Brand(1L, "ARTURO CALLE");
        Prices prices = new Prices(1L, brand, applicationDate.minusDays(1), applicationDate.plusDays(1),
                1, productId, 1, BigDecimal.valueOf(100.0), "EUR");

        when(pricesAdapterInterface.findPriceByProductIdAndBrandId(productId, brandId))
                .thenReturn(List.of(prices));

        Optional<Prices> result = priceUseCase.getPriceForProduct(applicationDate, productId, brandId);

        assertTrue(result.isPresent());
        Prices response = result.get();
        assertEquals(prices.getProductId(), response.getProductId());
        assertEquals(prices.getBrand().getBrandId(), response.getBrand().getBrandId());
        assertEquals(prices.getPriceList(), response.getPriceList());
        assertEquals(prices.getPrice(), response.getPrice());
        assertEquals(prices.getStartDate(), response.getStartDate());
        assertEquals(prices.getEndDate(), response.getEndDate());
        verify(pricesAdapterInterface).findPriceByProductIdAndBrandId(productId, brandId);
    }

    @Test
    void testGetPriceForProduct_PriceNotFound() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 2L;

        when(pricesAdapterInterface.findPriceByProductIdAndBrandId(productId, brandId))
                .thenReturn(List.of());

        Optional<Prices> result = priceUseCase.getPriceForProduct(applicationDate, productId, brandId);

        assertFalse(result.isPresent());
        verify(pricesAdapterInterface).findPriceByProductIdAndBrandId(productId, brandId);
    }
}