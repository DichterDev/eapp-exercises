package at.fhv.ecommerce.common.domain;

import java.time.Instant;
import java.util.UUID;

public interface DomainEvent {
    UUID id();

    Instant occurredAt();
}
