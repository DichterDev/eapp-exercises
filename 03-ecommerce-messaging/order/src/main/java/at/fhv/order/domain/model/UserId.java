package at.fhv.order.domain.model;

import at.fhv.common.domain.model.DomainId;

import java.util.UUID;

public record UserId(UUID value) implements DomainId<UUID> {
}
