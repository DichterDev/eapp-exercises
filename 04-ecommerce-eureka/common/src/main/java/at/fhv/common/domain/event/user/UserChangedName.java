package at.fhv.common.domain.event.user;

import java.time.Instant;
import java.util.UUID;


public record UserChangedName(UUID id, UUID userId, String old, Instant occurredAt)
        implements UserEvent {
    public UserChangedName(UUID userId, String old) {

        this(
                UUID.randomUUID(),
                userId,
                old,
                Instant.now());
    }
}