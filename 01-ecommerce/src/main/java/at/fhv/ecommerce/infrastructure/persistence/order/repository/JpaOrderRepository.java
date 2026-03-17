package at.fhv.ecommerce.infrastructure.persistence.order.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import at.fhv.ecommerce.infrastructure.persistence.order.entity.OrderEntity;

@Repository
public interface JpaOrderRepository extends JpaRepository<OrderEntity, UUID> {
    @Query("SELECT o from Order o LEFT JOIN FETCH o.items WHERE o.id = :id")
    Optional<OrderEntity> findByIdWithItems(UUID id);

    @Query("SELECT o from Order o LEFT JOIN FETCH o.items WHERE o.userId = :userId")
    List<OrderEntity> findByUserIdWithItems(UUID userId, Pageable pageable);
}
