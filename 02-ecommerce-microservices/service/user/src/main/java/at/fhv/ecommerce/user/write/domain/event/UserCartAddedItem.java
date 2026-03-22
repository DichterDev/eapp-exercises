package at.fhv.ecommerce.user.write.domain.event;

import java.time.Instant;
import java.util.UUID;
import at.fhv.ecommerce.common.domain.DomainEvent;
import at.fhv.ecommerce.user.write.domain.model.ProductId;
import at.fhv.ecommerce.user.write.domain.model.User;
import at.fhv.ecommerce.user.write.domain.model.UserId;

public record UserCartAddedItem(UUID id, UserId userId, ProductId productId, Instant occurredAt)
    implements DomainEvent {
    public UserCartAddedItem(User user, ProductId productId) {
        this(
            UUID.randomUUID(),
            user.getId(),
            productId,
            Instant.now()
        );
    }
}
