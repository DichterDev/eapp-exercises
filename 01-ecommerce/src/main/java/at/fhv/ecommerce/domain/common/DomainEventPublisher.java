package at.fhv.ecommerce.domain.common;

import java.util.List;

public interface DomainEventPublisher {
    void publish(List<DomainEvent> events);
}
