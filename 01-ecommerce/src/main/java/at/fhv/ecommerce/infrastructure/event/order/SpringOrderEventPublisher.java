package at.fhv.ecommerce.infrastructure.event.order;

import java.util.List;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import at.fhv.ecommerce.domain.common.DomainEvent;
import at.fhv.ecommerce.domain.order.ports.OrderEventPublisher;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SpringOrderEventPublisher implements OrderEventPublisher {
    private final ApplicationEventPublisher publisher;

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(event -> {
            publisher.publishEvent(event);
        });
    }

}
