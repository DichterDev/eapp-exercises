package at.fhv.ecommerce.domain.user.model;

import java.util.UUID;
import at.fhv.ecommerce.domain.common.DomainId;

public record UserId(UUID value) implements DomainId<UUID> {
    public static UserId generate() {
        return new UserId(UUID.randomUUID());
    }
}
