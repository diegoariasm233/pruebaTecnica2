package co.com.ecommerce.rest.controller;

import co.com.ecommerce.rest.model.ProductPriceRequest;
import co.com.ecommerce.rest.exception.ResourceNotFoundException;
import co.com.ecommerce.rest.model.ProductPriceResponse;
import co.com.ecommerce.service.ProductPriceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/product-prices")
public class ProductPriceController {

    @Autowired
    private ProductPriceService priceUseCase;

    @GetMapping
    public ResponseEntity<ProductPriceResponse> getProductPrice(@Valid @ModelAttribute ProductPriceRequest request) {
        LocalDateTime applicationDate = LocalDateTime.parse(request.getApplicationDate());
        return priceUseCase.getPriceForProduct(applicationDate, request.getProductId(),
                        request.getBrandId()).map(price -> new ProductPriceResponse(price.getProductId(),
                        price.getBrand().getBrandId(),price.getPriceList(),price.getStartDate(), price.getEndDate(),
                        price.getPrice())).map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("No price found for the given parameters."));
    }
}