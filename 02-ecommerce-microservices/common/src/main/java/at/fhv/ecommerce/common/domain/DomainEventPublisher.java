package at.fhv.ecommerce.common.domain;

import java.util.List;

public interface DomainEventPublisher {
    void publishAll(List<DomainEvent> events);
}
