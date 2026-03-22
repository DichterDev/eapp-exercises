package at.fhv.ecommerce.order.write.domain.model;

import java.util.UUID;
import at.fhv.ecommerce.common.domain.DomainId;

public record UserId(UUID value) implements DomainId<UUID> {
}
