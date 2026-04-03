package at.fhv.common.domain.event.order;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public record OrderPlaced(
    UUID id,
    UUID orderId,
    UUID userId,
    Map<UUID, Integer> orderItems,
    Instant occurredAt
) implements OrderEvent {

    public OrderPlaced(UUID orderId, UUID userId, Map<UUID, Integer> orderItems) {
        this(
            UUID.randomUUID(),
            orderId,
            userId,
            orderItems,
            Instant.now()
        );
    }
}
