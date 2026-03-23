package at.fhv.ecommerce.user.write.domain.model;

import java.util.UUID;
import at.fhv.ecommerce.common.domain.DomainId;

public record OrderId(UUID value) implements DomainId<UUID> {
}
