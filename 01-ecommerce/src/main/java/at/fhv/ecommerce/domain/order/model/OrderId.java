package at.fhv.ecommerce.domain.order.model;

import java.util.UUID;
import at.fhv.ecommerce.domain.common.DomainId;

public record OrderId(UUID value) implements DomainId<UUID> {
    public static OrderId generate() {
        return new OrderId(UUID.randomUUID());
    }
}
