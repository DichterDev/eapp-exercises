package at.fhv.order.infrastructure.persistence.repository;

import at.fhv.order.domain.model.OrderId;
import at.fhv.order.domain.port.OrderWriteRepository;
import at.fhv.order.infrastructure.persistence.mapper.OrderWriteMapper;
import at.fhv.order.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpringOrderWriteRepository implements OrderWriteRepository {

    private final JpaOrderRepository jpa;
    private final OrderWriteMapper mapper;

    @Override
    public Optional<Order> findById(OrderId id) {
        return jpa.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public void save(Order order) {
        jpa.save(mapper.toEntity(order));
    }
}