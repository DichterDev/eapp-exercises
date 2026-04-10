package at.fhv.order.domain.model;

import at.fhv.common.domain.model.DomainId;

import java.util.UUID;

public record OrderId(UUID value) implements DomainId<UUID> {
}
