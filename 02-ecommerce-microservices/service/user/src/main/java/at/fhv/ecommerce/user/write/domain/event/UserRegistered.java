package at.fhv.ecommerce.user.write.domain.event;

import java.time.Instant;
import java.util.UUID;
import at.fhv.ecommerce.common.domain.DomainEvent;
import at.fhv.ecommerce.user.write.domain.model.User;
import at.fhv.ecommerce.user.write.domain.model.UserId;

public record UserRegistered(UUID id, UserId userId, String name, Instant occurredAt)
    implements DomainEvent {
    public UserRegistered(User user) {
        this(
            UUID.randomUUID(),
            user.getId(),
            user.getName(),
            Instant.now()
        );
    }
}
