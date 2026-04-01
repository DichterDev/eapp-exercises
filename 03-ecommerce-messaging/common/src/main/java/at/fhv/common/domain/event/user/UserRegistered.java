package at.fhv.common.domain.event.user;

import at.fhv.common.domain.event.DomainEvent;

import java.time.Instant;
import java.util.UUID;


public record UserRegistered(
        UUID id,
        UUID userId,
        String name,
        Instant occurredAt)
        implements DomainEvent {
    public UserRegistered(UUID userId, String name) {
        this(UUID.randomUUID(), userId, name, Instant.now());
    }
}