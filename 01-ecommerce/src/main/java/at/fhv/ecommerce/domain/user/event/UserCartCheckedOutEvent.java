package at.fhv.ecommerce.domain.user.event;

import java.time.Instant;
import java.util.UUID;
import at.fhv.ecommerce.domain.order.model.OrderId;
import at.fhv.ecommerce.domain.user.model.User;
import at.fhv.ecommerce.domain.user.model.UserId;

public record UserCartCheckedOutEvent(
    UUID id,
    UserId userId,
    OrderId orderId,
    Instant occurredAt
)
    implements UserEvent {
    public UserCartCheckedOutEvent(User user, OrderId orderId) {
        this(
            UUID.randomUUID(),
            user.getId(),
            orderId,
            Instant.now()
        );
    }
}
