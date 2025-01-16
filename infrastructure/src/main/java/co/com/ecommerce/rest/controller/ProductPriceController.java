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
        return productPriceService.getPriceForProduct(date, productId, brandId).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}