package at.fhv.ecommerce.product.write.infrastructure.messaging;

import java.util.List;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import at.fhv.ecommerce.common.domain.DomainEvent;
import at.fhv.ecommerce.product.write.domain.port.ProductEventPublisher;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SpringProductEventPublisher implements ProductEventPublisher {
    private final ApplicationEventPublisher publisher;

    @Override
    public void publishAll(List<DomainEvent> events) {
        events.forEach(event -> {
            publisher.publishEvent(event);
        });
    }

}
