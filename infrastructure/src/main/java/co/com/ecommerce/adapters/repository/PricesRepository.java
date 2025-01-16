package co.com.ecommerce.adapters.repository;

import co.com.ecommerce.adapters.entities.PricesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PricesRepository extends CrudRepository<PricesEntity, Long> {


    Optional<PricesEntity> findTopByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrand_IdOrderByPriorityDesc(
            LocalDateTime applicationDateForStart, LocalDateTime applicationDateForEnd, Long productId, Long brandId);

    /* Here is the option to pass only one date parameter.
        @Query("""
            SELECT p
            FROM PricesEntity p
            WHERE p.startDate <= :applicationDate
              AND p.endDate >= :applicationDate
              AND p.productId = :productId
              AND p.brand.id = :brandId
            ORDER BY p.priority DESC
            LIMIT 1
            """)
        Optional<PricesEntity> findTopByApplicationDateAndProductIdAndBrandId(
                @Param("applicationDate") LocalDateTime applicationDate,
                @Param("productId") Long productId,
                @Param("brandId") Long brandId
        );
    */
}
