package at.fhv.product.domain.model;

import java.util.UUID;
import at.fhv.common.domain.model.DomainId;

public record ProductId(UUID value) implements DomainId<UUID> {

    public static ProductId generate() {
        return new ProductId(UUID.randomUUID());
    }
}