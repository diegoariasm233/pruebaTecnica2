package co.com.ecommerce.external;

import co.com.ecommerce.model.Prices;

import java.util.List;

public interface PricesAdapterInterface {
    List<Prices> findPriceByProductIdAndBrandId(Long productId, Long brandId);
}
