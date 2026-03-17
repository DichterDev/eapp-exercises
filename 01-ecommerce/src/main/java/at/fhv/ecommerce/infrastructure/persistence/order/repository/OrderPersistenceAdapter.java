package at.fhv.ecommerce.infrastructure.persistence.order.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import at.fhv.ecommerce.domain.order.model.Order;
import at.fhv.ecommerce.domain.order.model.OrderId;
import at.fhv.ecommerce.domain.order.ports.OrderRepository;
import at.fhv.ecommerce.domain.user.model.UserId;
import at.fhv.ecommerce.infrastructure.persistence.order.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements OrderRepository {

    private final JpaOrderRepository jpa;
    private final OrderMapper mapper;

    @Override
    public void save(Order model) {
        var entity = mapper.toEntity(model);
        jpa.save(entity);
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return jpa.findById(id.value()).map(mapper::toModel);
    }

    @Override
    public Optional<Order> findByIdWithItems(OrderId id) {
        return jpa.findByIdWithItems(id.value()).map(mapper::toModel);
    }

    @Override
    public List<Order> findByUserIdWithItems(UserId userId, Integer page, Integer size) {
        return jpa.findByUserIdWithItems(userId.value(), PageRequest.of(page, size))
            .stream()
            .map(mapper::toModel)
            .toList();
    }
}
