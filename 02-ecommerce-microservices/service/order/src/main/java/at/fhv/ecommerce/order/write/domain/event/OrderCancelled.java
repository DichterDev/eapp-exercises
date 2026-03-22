package at.fhv.ecommerce.order.write.domain.event;

import java.time.Instant;
import java.util.UUID;
import at.fhv.ecommerce.order.write.domain.model.Order;
import at.fhv.ecommerce.order.write.domain.model.OrderId;

public record OrderCancelled(UUID id, OrderId orderId, Instant occurredAt) implements OrderEvent {
    public OrderCancelled(Order order) {
        this(
            UUID.randomUUID(),
            order.getId(),
            Instant.now()
        );
    }
}
