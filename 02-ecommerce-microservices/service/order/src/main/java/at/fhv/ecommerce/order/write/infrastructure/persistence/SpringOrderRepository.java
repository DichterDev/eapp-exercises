package at.fhv.ecommerce.order.write.infrastructure.persistence;

import java.util.Optional;
import org.springframework.stereotype.Component;
import at.fhv.ecommerce.order.write.domain.model.Order;
import at.fhv.ecommerce.order.write.domain.model.OrderId;
import at.fhv.ecommerce.order.write.domain.port.OrderRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SpringOrderRepository implements OrderRepository {
    private final JpaOrderRepository jpa;
    private final OrderMapper mapper;

    @Override
    public void save(Order order) {
        jpa.save(mapper.toEntity(order));
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return jpa.findById(id.value()).map(mapper::toModel);
    }
}
