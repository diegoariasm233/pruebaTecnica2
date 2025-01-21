package co.com.ecommerce.adapters.repository;

import co.com.ecommerce.adapters.entities.PriceEntity;
import co.com.ecommerce.external.PriceAdapterInterface;
import co.com.ecommerce.model.Price;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PriceAdapterImpl implements PriceAdapterInterface {

    private final PriceRepository priceRepository;

    public PriceAdapterImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }


    @Override
    public List<Price> findPriceByProductIdAndBrandIdAndApplicationDate(LocalDateTime applicationDate,
                                                      Long productId,
                                                      Long brandId) {
        return priceRepository
                .findAllByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrand_Id(applicationDate,
                        applicationDate, productId, brandId).stream().map(PriceEntity::toDomainModel).toList();
    }
}
