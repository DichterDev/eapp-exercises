package at.fhv.ecommerce.order.write.infrastructure.messaging;

import java.util.List;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import at.fhv.ecommerce.common.domain.DomainEvent;
import at.fhv.ecommerce.order.write.domain.port.OrderEventPublisher;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SpringOrderEventPublisher implements OrderEventPublisher {
    private final ApplicationEventPublisher publisher;

    @Override
    public void publishAll(List<DomainEvent> events) {
        events.forEach(event -> {
            publisher.publishEvent(event);
        });
    }
}
