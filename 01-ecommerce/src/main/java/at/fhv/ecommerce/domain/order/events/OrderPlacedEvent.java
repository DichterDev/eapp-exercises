package at.fhv.ecommerce.domain.order.events;

import java.time.Instant;
import java.util.UUID;
import at.fhv.ecommerce.domain.order.model.Order;
import at.fhv.ecommerce.domain.order.model.OrderId;
import at.fhv.ecommerce.domain.user.model.UserId;

public record OrderPlacedEvent(
    UUID id,
    OrderId orderId,
    UserId userId,
    Instant occurredAt
) implements OrderEvent {
    public OrderPlacedEvent(Order order) {
        this(
            UUID.randomUUID(),
            order.getId(),
            order.getUserId(),
            Instant.now()
        );
    }
}
