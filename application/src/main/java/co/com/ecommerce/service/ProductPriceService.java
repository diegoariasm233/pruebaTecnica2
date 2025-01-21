package co.com.ecommerce.service;

import co.com.ecommerce.external.PriceAdapterInterface;
import co.com.ecommerce.model.Price;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

@Service
public class ProductPriceService {

    private final PriceAdapterInterface priceAdapterInterface;

    public ProductPriceService(PriceAdapterInterface priceAdapterInterface) {
        this.priceAdapterInterface = priceAdapterInterface;
    }


    public Optional<Price> getPriceForProduct(LocalDateTime applicationDate,
                                              Long productId,
                                              Long brandId){

        return priceAdapterInterface.findPriceByProductIdAndBrandIdAndApplicationDate(applicationDate,
                        productId, brandId)
                .stream()
                .max(Comparator.comparingInt(Price::getPriority));
    }
}
