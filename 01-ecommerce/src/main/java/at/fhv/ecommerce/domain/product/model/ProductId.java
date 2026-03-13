package at.fhv.ecommerce.domain.product.model;

import java.util.UUID;
import at.fhv.ecommerce.domain.common.DomainId;

public record ProductId(UUID value) implements DomainId<UUID> {

    public static ProductId generate() {
        return new ProductId(UUID.randomUUID());
    }
}
