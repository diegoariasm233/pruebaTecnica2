package co.com.ecommerce.adapters.repository;

import co.com.ecommerce.adapters.entities.PricesEntity;
import co.com.ecommerce.external.PricesAdapterInterface;
import co.com.ecommerce.model.Prices;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PricesAdapterImpl implements PricesAdapterInterface {

    private final PricesRepository pricesRepository;

    public PricesAdapterImpl(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }


    @Override
    public List<Prices> findPriceByProductIdAndBrandId(Long productId,
                                                       Long brandId) {
        return pricesRepository
                .findAllByProductIdAndBrand_Id(productId, brandId).stream().map(PricesEntity::toDomainModel).toList();
    }
}
