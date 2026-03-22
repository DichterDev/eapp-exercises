package at.fhv.ecommerce.product.write.domain.model;

import java.util.UUID;
import at.fhv.ecommerce.common.domain.DomainId;

public record ProductId(UUID value) implements DomainId<UUID> {
}
