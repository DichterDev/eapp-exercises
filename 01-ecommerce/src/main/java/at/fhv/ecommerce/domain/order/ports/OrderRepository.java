package at.fhv.ecommerce.domain.order.ports;

import java.util.Optional;
import at.fhv.ecommerce.domain.common.BaseRepository;
import at.fhv.ecommerce.domain.order.model.Order;
import at.fhv.ecommerce.domain.order.model.OrderId;
import at.fhv.ecommerce.domain.order.model.OrderItem;

public interface OrderRepository extends BaseRepository<Order, OrderId> {
    Optional<Order> findByIdWithItems(OrderId id);
}
