package at.fhv.common.domain.event.product;

import java.time.Instant;
import java.util.UUID;

public record ProductFailedToReduceStock(
        UUID id,
        UUID productId,
        UUID orderId,
        Integer requestedAmount,
        String reason,
        Instant occurredAt
) implements ProductEvent {

    public ProductFailedToReduceStock(UUID productId, UUID orderId, Integer requestedAmount, String reason) {
        this(
                UUID.randomUUID(),
                productId,
                orderId,
                requestedAmount,
                reason,
                Instant.now()
        );
    }
}
