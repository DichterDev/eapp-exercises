package at.fhv.ecommerce.domain.common;

import java.time.Instant;
import java.util.UUID;

public interface DomainEvent {
    UUID id();

    Instant occurredAt();
}
