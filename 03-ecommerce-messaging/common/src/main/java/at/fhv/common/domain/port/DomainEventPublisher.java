package at.fhv.common.domain.port;

import at.fhv.common.domain.event.DomainEvent;

import java.util.List;

public interface DomainEventPublisher {
    void publishAll(List<DomainEvent> events);
}
