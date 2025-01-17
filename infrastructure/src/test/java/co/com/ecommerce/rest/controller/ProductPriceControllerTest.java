package co.com.ecommerce.rest.controller;

import co.com.ecommerce.model.ProductPriceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ProductPriceControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String url = "/api/v1/product-prices";

    @Test
    void testGetPriceForProduct_PriceFound10HrsDay14() {
        BigDecimal assertPrice = BigDecimal.valueOf(35.50).setScale(2, RoundingMode.HALF_UP);
        ProductPriceResponse response = this.restTemplate
                .getForEntity("http://localhost:" + port + url +
                                "?date=2020-06-14T10:00:00&productId=35455&brandId=1",
                ProductPriceResponse.class).getBody();

        assertNotNull(response);
        assertNotNull(response.getPrice());
        assertEquals(assertPrice, response.getPrice());
    }

    @Test
    void testGetPriceForProduct_PriceFound16HrsDay14() {
        ProductPriceResponse response = this.restTemplate
                .getForEntity("http://localhost:" + port + url +
                                "?date=2020-06-14T16:00:00&productId=35455&brandId=1",
                        ProductPriceResponse.class).getBody();

        assertNotNull(response);
        assertNotNull(response.getPrice());
        assertEquals(BigDecimal.valueOf(25.45), response.getPrice());
    }

    @Test
    void testGetPriceForProduct_PriceFound21HrsDay14() {
        BigDecimal assertPrice = BigDecimal.valueOf(35.50).setScale(2, RoundingMode.HALF_UP);
        ProductPriceResponse response = this.restTemplate
                .getForEntity("http://localhost:" + port + url +
                                "?date=2020-06-14T21:00:00&productId=35455&brandId=1",
                        ProductPriceResponse.class).getBody();

        assertNotNull(response);
        assertNotNull(response.getPrice());
        assertEquals(assertPrice, response.getPrice());
    }

    @Test
    void testGetPriceForProduct_PriceFound10HrsDay15() {
        BigDecimal assertPrice = BigDecimal.valueOf(30.50).setScale(2, RoundingMode.HALF_UP);
        ProductPriceResponse response = this.restTemplate
                .getForEntity("http://localhost:" + port + url +
                                "?date=2020-06-15T10:00:00&productId=35455&brandId=1",
                        ProductPriceResponse.class).getBody();

        assertNotNull(response);
        assertNotNull(response.getPrice());
        assertEquals(assertPrice, response.getPrice());
    }

    @Test
    void testGetPriceForProduct_PriceFound21HrsDay16() {
        ProductPriceResponse response = this.restTemplate
                .getForEntity("http://localhost:" + port + url +
                                "?date=2020-06-16T21:00:00&productId=35455&brandId=1",
                        ProductPriceResponse.class).getBody();

        assertNotNull(response);
        assertNotNull(response.getPrice());
        assertEquals(BigDecimal.valueOf(38.95), response.getPrice());
    }


    @Test
    void testGetPriceForProduct_PriceNotFound21HrsDay16Ano21() {
        ResponseEntity<ProductPriceResponse> response = this.restTemplate
                .getForEntity("http://localhost:" + port + url +
                                "?date=2021-06-16T21:00:00&productId=35455&brandId=1",
                        ProductPriceResponse.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

}