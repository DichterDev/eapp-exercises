package at.fhv.ecommerce.order.write.domain.event;

import java.time.Instant;
import java.util.UUID;
import at.fhv.ecommerce.order.write.domain.model.Order;
import at.fhv.ecommerce.order.write.domain.model.OrderId;
import at.fhv.ecommerce.order.write.domain.model.UserId;

public record OrderPlaced(UUID id, OrderId orderId, UserId userId, Instant occurredAt)
    implements OrderEvent {
    public OrderPlaced(Order order) {
        this(
            UUID.randomUUID(),
            order.getId(),
            order.getUserId(),
            Instant.now()
        );
    }
}
