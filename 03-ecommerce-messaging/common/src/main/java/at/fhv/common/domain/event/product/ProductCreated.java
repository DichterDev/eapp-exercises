package at.fhv.common.domain.event.product;

import at.fhv.common.domain.model.Money;

import java.time.Instant;
import java.util.UUID;

public record ProductCreated(
        UUID id,
        UUID productId,
        String name,
        String description,
        Money price,
        Integer stock,
        Instant occurredAt

) implements ProductEvent {

    public ProductCreated(UUID productId, String name, String description, Money price, Integer stock) {
        this(
                UUID.randomUUID(),
                productId,
                name,
                description,
                price,
                stock,
                Instant.now()
        );
    }
}
