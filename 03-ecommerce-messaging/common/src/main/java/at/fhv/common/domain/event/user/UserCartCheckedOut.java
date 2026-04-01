package at.fhv.common.domain.event.user;

import at.fhv.common.domain.event.DomainEvent;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public record UserCartCheckedOut(
        UUID id,
        UUID userId,
        List<UserCartCheckedOutItem> items,
        Instant occurredAt
) implements DomainEvent {

    public UserCartCheckedOut(UUID userId, List<UserCartCheckedOutItem> items) {
        this(UUID.randomUUID(), userId, items, Instant.now());
    }
}