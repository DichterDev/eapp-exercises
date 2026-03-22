package at.fhv.ecommerce.user.write.domain.event;

import java.time.Instant;
import java.util.UUID;
import at.fhv.ecommerce.user.write.domain.model.User;
import at.fhv.ecommerce.user.write.domain.model.UserId;

public record UserChangedName(UUID id, UserId userId, String old, Instant occurredAt)
    implements UserEvent {
    public UserChangedName(User user, String old) {
        this(
            UUID.randomUUID(),
            user.getId(),
            old,
            Instant.now()
        );
    }
}
