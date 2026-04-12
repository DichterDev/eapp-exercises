package at.fhv.common.domain.event.order;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record OrderFailed(
    UUID id,
    UUID orderId,
    Instant occurredAt
) implements OrderEvent {
    public OrderFailed(UUID orderId) {
        this(
            UUID.randomUUID(),
            orderId,
            Instant.now()
        );
    }
}
