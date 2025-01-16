package co.com.ecommerce.interfaces;

import co.com.ecommerce.model.Prices;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PricesAdapterInterface {
    Optional<Prices> findPriceByDateAndProductIdAndBrandId(LocalDateTime applicationDate, Long productId, Long brandId);
}
