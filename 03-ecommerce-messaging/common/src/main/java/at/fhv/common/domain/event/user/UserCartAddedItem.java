package at.fhv.common.domain.event.user;

import at.fhv.common.domain.event.DomainEvent;

import java.time.Instant;
import java.util.UUID;


public record UserCartAddedItem(UUID id, UUID userId, UUID productId, Instant occurredAt)
        implements DomainEvent {
    public UserCartAddedItem(UUID userId, UUID productId) {
        this(UUID.randomUUID(), userId, productId, Instant.now());
    }
}