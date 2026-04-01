package at.fhv.common.domain.event;

import java.time.Instant;
import java.util.UUID;

public interface DomainEvent {
    UUID id();

    Instant occurredAt();
}