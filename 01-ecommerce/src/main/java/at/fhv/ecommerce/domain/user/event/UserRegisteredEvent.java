package at.fhv.ecommerce.domain.user.event;

import java.time.Instant;
import java.util.UUID;
import at.fhv.ecommerce.domain.user.model.User;
import at.fhv.ecommerce.domain.user.model.UserId;

public record UserRegisteredEvent(UUID id, UserId userId, Instant occurredAt) implements UserEvent {
    public UserRegisteredEvent(User user) {
        this(
            UUID.randomUUID(),
            user.getId(),
            Instant.now()
        );
    }
}
