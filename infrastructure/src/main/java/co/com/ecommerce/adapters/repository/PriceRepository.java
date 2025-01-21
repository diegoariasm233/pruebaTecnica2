package co.com.ecommerce.adapters.repository;

import co.com.ecommerce.adapters.entities.PriceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends CrudRepository<PriceEntity, Long> {

    List<PriceEntity> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrand_Id(
            LocalDateTime applicationDateForStart, LocalDateTime applicationDateForEnd, Long productId, Long brandId);
}
