package at.fhv.common.domain.event.order;

import java.time.Instant;
import java.util.UUID;

public record OrderCancelled(
        UUID id,
        UUID orderId,
        Instant occurredAt
) implements OrderEvent{
    public OrderCancelled(UUID orderId) {
        this(
            UUID.randomUUID(),
            orderId,
            Instant.now()
        );
    }

}
