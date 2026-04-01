package at.fhv.common.domain.event.order;

import java.time.Instant;
import java.util.UUID;

public record OrderCompleted(
        UUID id,
        UUID orderId,
        Instant occurredAt
) implements OrderEvent {
    public OrderCompleted(UUID orderId) {
        this(
                UUID.randomUUID(),
                orderId,
                Instant.now()
        );
    }
}
