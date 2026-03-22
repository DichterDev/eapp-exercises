package at.fhv.ecommerce.order.write.domain.port;

import java.util.Optional;
import at.fhv.ecommerce.order.write.domain.model.Order;
import at.fhv.ecommerce.order.write.domain.model.OrderId;

public interface OrderRepository {
    void save(Order order);

    Optional<Order> findById(OrderId id);
}
