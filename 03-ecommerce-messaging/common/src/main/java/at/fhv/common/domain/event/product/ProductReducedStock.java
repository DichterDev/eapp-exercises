package at.fhv.common.domain.event.product;

import java.time.Instant;
import java.util.UUID;

public record ProductReducedStock(
        UUID id,
        UUID productId,
        UUID orderId,
        Integer by,
        Instant occurredAt
)
implements ProductEvent {
    public ProductReducedStock(UUID productId, UUID orderId, Integer by) {
        this(
                UUID.randomUUID(),
                productId,
                orderId,
                by,
                Instant.now()
        );
    }
}
