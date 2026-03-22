package at.fhv.ecommerce.user.write.domain.event;

import java.time.Instant;
import java.util.UUID;
import at.fhv.ecommerce.common.domain.DomainEvent;
import at.fhv.ecommerce.user.write.domain.model.User;
import at.fhv.ecommerce.user.write.domain.model.UserId;

public record UserCartCheckedOut(UUID id, UserId userId, Instant occurredAt)
    implements DomainEvent {
    public UserCartCheckedOut(User user) {
        this(
            UUID.randomUUID(),
            user.getId(),
            Instant.now()
        );
    }
}
