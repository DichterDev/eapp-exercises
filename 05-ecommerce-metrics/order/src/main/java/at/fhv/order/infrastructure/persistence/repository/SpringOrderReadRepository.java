package at.fhv.order.infrastructure.persistence.repository;

import at.fhv.order.domain.port.OrderReadRepository;
import at.fhv.order.infrastructure.persistence.mapper.OrderReadMapper;
import at.fhv.order.projection.Order;
import at.fhv.order.projection.OrderDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SpringOrderReadRepository implements OrderReadRepository {

    private final JpaOrderRepository jpa;
    private final OrderReadMapper mapper;

    @Override
    public Optional<Order> get(UUID id) {
        return jpa.findById(id).map(mapper::toProjection);
    }

    @Override
    public Optional<OrderDetail> getDetail(UUID id) {
        return jpa.findById(id).map(mapper::toDetail);
    }

    @Override
    public List<OrderDetail> getByUserId(UUID userId) {
        return jpa.findByUserId(userId)
            .stream()
            .map(mapper::toDetail)
            .toList();
    }
}
