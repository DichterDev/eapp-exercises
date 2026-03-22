package at.fhv.ecommerce.order.read.projection.repository;

import java.util.List;
import java.util.UUID;
import at.fhv.ecommerce.order.read.projection.Order;
import at.fhv.ecommerce.order.read.projection.OrderDetail;

public interface OrderRepository {
    Order findById(UUID orderId);

    OrderDetail findDetailById(UUID orderId);

    List<OrderDetail> findByUserId(UUID userId);
}
