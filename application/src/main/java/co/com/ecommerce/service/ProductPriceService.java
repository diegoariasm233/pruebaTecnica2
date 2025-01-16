package co.com.ecommerce.service;

import co.com.ecommerce.interfaces.PricesAdapterInterface;
import co.com.ecommerce.model.ProductPriceResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProductPriceService {

    private final PricesAdapterInterface pricesAdapterInterface;

    public ProductPriceService(PricesAdapterInterface pricesAdapterInterface) {
        this.pricesAdapterInterface = pricesAdapterInterface;
    }


    public Optional<ProductPriceResponse> getPriceForProduct(LocalDateTime applicationDate,
                                                             Long productId,
                                                             Long brandId){
        return pricesAdapterInterface.findPriceByDateAndProductIdAndBrandId(applicationDate, productId, brandId)
                .map(prices -> new ProductPriceResponse(prices.getProductId(), prices.getBrand().getBrandId(),
                        prices.getPriceList(),
                        prices.getStartDate(), prices.getEndDate(), prices.getPrice()));
    }
}
