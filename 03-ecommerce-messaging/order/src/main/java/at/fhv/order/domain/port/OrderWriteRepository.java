package at.fhv.order.domain.port;

import at.fhv.order.domain.model.OrderId;
import at.fhv.order.domain.model.Order;

import java.util.Optional;

public interface OrderWriteRepository {

    void save(Order order);

    Optional<Order> findById(OrderId id);
}
