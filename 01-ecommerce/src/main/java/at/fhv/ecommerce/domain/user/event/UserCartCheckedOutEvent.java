package at.fhv.ecommerce.domain.user.event;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import at.fhv.ecommerce.domain.user.model.CartItem;
import at.fhv.ecommerce.domain.user.model.User;
import at.fhv.ecommerce.domain.user.model.UserId;

public record UserCartCheckedOutEvent(
    UUID id,
    UserId userId,
    Instant occurredAt
)
    implements UserEvent {
    public UserCartCheckedOutEvent(User user) {
        this(
            UUID.randomUUID(),
            user.getId(),
            Instant.now()
        );
    }
}
