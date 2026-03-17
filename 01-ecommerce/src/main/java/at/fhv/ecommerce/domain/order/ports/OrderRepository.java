package at.fhv.ecommerce.domain.order.ports;

import java.util.List;
import java.util.Optional;
import at.fhv.ecommerce.domain.common.BaseRepository;
import at.fhv.ecommerce.domain.order.model.Order;
import at.fhv.ecommerce.domain.order.model.OrderId;
import at.fhv.ecommerce.domain.order.model.OrderItem;
import at.fhv.ecommerce.domain.user.model.UserId;

public interface OrderRepository extends BaseRepository<Order, OrderId> {
    Optional<Order> findByIdWithItems(OrderId id);

    List<Order> findByUserIdWithItems(UserId userId, Integer page, Integer size);
}
