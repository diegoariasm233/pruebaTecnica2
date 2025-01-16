package co.com.ecommerce.adapters.repository;

import co.com.ecommerce.adapters.entities.PricesEntity;
import co.com.ecommerce.interfaces.PricesAdapterInterface;
import co.com.ecommerce.model.Prices;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class PricesAdapterImpl implements PricesAdapterInterface {

    private final PricesRepository pricesRepository;

    public PricesAdapterImpl(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }


    @Override
    public Optional<Prices> findPriceByDateAndProductIdAndBrandId(LocalDateTime applicationDate,
                                                                  Long productId,
                                                                  Long brandId) {
        return pricesRepository
                .findTopByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrand_IdOrderByPriorityDesc(
                        applicationDate, applicationDate,  productId, brandId).map(PricesEntity::toDomainModel);
    }
}
