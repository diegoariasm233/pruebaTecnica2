package co.com.ecommerce.service;

import co.com.ecommerce.interfaces.PricesAdapterInterface;
import co.com.ecommerce.model.Prices;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProductPriceService {

    private final PricesAdapterInterface pricesAdapterInterface;

    public ProductPriceService(PricesAdapterInterface pricesAdapterInterface) {
        this.pricesAdapterInterface = pricesAdapterInterface;
    }


    public Optional<Prices> getPriceForProduct(LocalDateTime applicationDate, Long productId, Long brandId){
        return pricesAdapterInterface.findPriceByDateAndProductIdAndBrandId(applicationDate, productId, brandId);
    }
}
