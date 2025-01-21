package co.com.ecommerce.external;

import co.com.ecommerce.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceAdapterInterface {
    List<Price> findPriceByProductIdAndBrandIdAndApplicationDate(LocalDateTime applicationDate,
                                                                 Long productId, Long brandId);
}
