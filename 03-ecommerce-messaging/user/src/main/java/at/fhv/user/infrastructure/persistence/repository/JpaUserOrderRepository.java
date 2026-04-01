package at.fhv.user.infrastructure.persistence.repository;

import at.fhv.user.infrastructure.persistence.entity.UserOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaUserOrderRepository extends JpaRepository<UserOrderEntity, UUID> {
    List<UserOrderEntity> findByUserId(UUID userId);

    Optional<UserOrderEntity> findByOrderId(UUID orderId);
}