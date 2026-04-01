package at.fhv.common.domain.event.product;

import java.time.Instant;
import java.util.UUID;

public record ProductIncreasedStock(
        UUID id,
        UUID productId,
        Integer by,
        Instant occurredAt
)

implements ProductEvent {
    public ProductIncreasedStock(UUID productId, Integer by) {
        this(
                UUID.randomUUID(),
                productId,
                by,
                Instant.now()
        );
    }
}
