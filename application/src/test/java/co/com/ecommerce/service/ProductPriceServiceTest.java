package co.com.ecommerce.service;

import co.com.ecommerce.interfaces.PricesAdapterInterface;
import co.com.ecommerce.model.Brand;
import co.com.ecommerce.model.Prices;
import co.com.ecommerce.model.ProductPriceResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class ProductPriceServiceTest {

    @Mock
    private PricesAdapterInterface pricesAdapterInterface;

    @InjectMocks
    private ProductPriceService productPriceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPriceForProduct_PriceFound() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 2L;

        Prices mockPrices = mock(Prices.class);
        Brand mockBrand = mock(Brand.class);

        when(mockPrices.getProductId()).thenReturn(productId);
        when(mockBrand.getBrandId()).thenReturn(brandId);
        when(mockPrices.getBrand()).thenReturn(mockBrand);
        when(mockPrices.getPriceList()).thenReturn(1);
        when(mockPrices.getStartDate()).thenReturn(applicationDate.minusDays(1));
        when(mockPrices.getEndDate()).thenReturn(applicationDate.plusDays(1));
        when(mockPrices.getPrice()).thenReturn(BigDecimal.valueOf(100.0));

        when(pricesAdapterInterface.findPriceByDateAndProductIdAndBrandId(applicationDate, productId, brandId))
                .thenReturn(Optional.of(mockPrices));

        Optional<ProductPriceResponse> result = productPriceService.getPriceForProduct(applicationDate, productId, brandId);

        assertTrue(result.isPresent());
        ProductPriceResponse response = result.get();
        assertEquals(productId, response.getProductId());
        assertEquals(brandId, response.getBrandId());
        assertEquals(1, response.getPriceList());
        assertEquals(BigDecimal.valueOf(100.0), response.getPrice());
        assertEquals(applicationDate.minusDays(1), response.getStartDate());
        assertEquals(applicationDate.plusDays(1), response.getEndDate());

        verify(pricesAdapterInterface).findPriceByDateAndProductIdAndBrandId(applicationDate, productId, brandId);
    }

    @Test
    void testGetPriceForProduct_PriceNotFound() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 2L;

        when(pricesAdapterInterface.findPriceByDateAndProductIdAndBrandId(applicationDate, productId, brandId))
                .thenReturn(Optional.empty());

        Optional<ProductPriceResponse> result = productPriceService.getPriceForProduct(applicationDate, productId, brandId);

        assertFalse(result.isPresent());
        verify(pricesAdapterInterface).findPriceByDateAndProductIdAndBrandId(applicationDate, productId, brandId);
    }
}