package at.fhv.common.domain.event.product;

import at.fhv.common.domain.model.Money;

import java.time.Instant;
import java.util.UUID;

public record ProductChangedPrice(
        UUID id,
        UUID productId,
        Money newPrice,
        Instant occurredAt
)
        implements ProductEvent {
        public ProductChangedPrice(UUID productId, Money newPrice) {
            this(
                    UUID.randomUUID(),
                    productId,
                    newPrice,
                    Instant.now()
            );
        }

}
