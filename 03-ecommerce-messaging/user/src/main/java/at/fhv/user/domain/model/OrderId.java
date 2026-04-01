package at.fhv.user.domain.model;

import java.util.UUID;
import at.fhv.common.domain.model.DomainId;

public record OrderId(UUID value) implements DomainId<UUID> {
}