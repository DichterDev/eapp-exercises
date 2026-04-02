package at.fhv.common.domain.event.user;

import at.fhv.common.domain.event.DomainEvent;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public record UserCartCheckedOut(
    UUID id,
    UUID userId,
    Map<UUID, Integer> items,
    Instant occurredAt
) implements DomainEvent {

    public UserCartCheckedOut(UUID userId, Map<UUID, Integer> items) {
        this(UUID.randomUUID(), userId, items, Instant.now());
    }
}
