package at.fhv.ecommerce.domain.order.events;

import java.time.Instant;
import java.util.UUID;
import at.fhv.ecommerce.domain.order.model.Order;
import at.fhv.ecommerce.domain.order.model.OrderId;

public record OrderCancelledEvent(UUID id, OrderId orderId, Instant occurredAt)
    implements OrderEvent {
    public OrderCancelledEvent(Order order) {
        this(
            UUID.randomUUID(),
            order.getId(),
            Instant.now()
        );
    }
}
