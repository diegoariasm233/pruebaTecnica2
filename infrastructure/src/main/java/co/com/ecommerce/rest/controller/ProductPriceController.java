package co.com.ecommerce.rest.controller;

import co.com.ecommerce.service.ProductPriceService;
import co.com.ecommerce.model.ProductPriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product-prices")
public class ProductPriceController {

    @Autowired
    private ProductPriceService productPriceService;

    @GetMapping
    public ResponseEntity<ProductPriceResponse> getProductPrice(
            @RequestParam("date") String applicationDate,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId) {

        LocalDateTime date = LocalDateTime.parse(applicationDate);
        Optional<ProductPriceResponse> productPrice = productPriceService.getPriceForProduct(date, productId, brandId)
                .map(prices -> new ProductPriceResponse(prices.getProductId(), prices.getBrand().getBrandId(),
                        prices.getPriceList(),
                        prices.getStartDate(), prices.getEndDate(), prices.getPrice()));
        return productPrice.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}