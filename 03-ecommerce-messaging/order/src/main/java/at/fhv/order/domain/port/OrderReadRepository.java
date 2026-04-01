package at.fhv.order.domain.port;

import at.fhv.order.projection.Order;
import at.fhv.order.projection.OrderDetail;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderReadRepository {

    Optional<Order> get(UUID id);
    Optional<OrderDetail> getDetail(UUID id);
    List<Order> getByUserId(UUID userId);
}
