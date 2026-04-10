package at.fhv.user.domain.model;

import java.util.UUID;
import at.fhv.common.domain.model.DomainId;

public record UserId(UUID value) implements DomainId<UUID> {
}