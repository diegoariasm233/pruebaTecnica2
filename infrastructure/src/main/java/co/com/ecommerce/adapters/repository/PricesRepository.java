package co.com.ecommerce.adapters.repository;

import co.com.ecommerce.adapters.entities.PricesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PricesRepository extends CrudRepository<PricesEntity, Long> {

    List<PricesEntity> findAllByProductIdAndBrand_Id(Long productId, Long brandId);

}
