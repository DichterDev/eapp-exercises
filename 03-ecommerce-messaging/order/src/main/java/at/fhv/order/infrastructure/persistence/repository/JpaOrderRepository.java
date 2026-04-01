package at.fhv.order.infrastructure.persistence.repository;

import at.fhv.order.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaOrderRepository extends JpaRepository<OrderEntity, UUID> {

    List<OrderEntity> findByUserId(UUID userId);
}
