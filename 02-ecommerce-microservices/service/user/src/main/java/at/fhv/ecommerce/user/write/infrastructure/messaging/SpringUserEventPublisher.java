package at.fhv.ecommerce.user.write.infrastructure.messaging;

import java.util.List;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import at.fhv.ecommerce.common.domain.DomainEvent;
import at.fhv.ecommerce.user.write.domain.port.UserEventPublisher;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SpringUserEventPublisher implements UserEventPublisher {
    private final ApplicationEventPublisher publisher;

    @Override
    public void publishAll(List<DomainEvent> events) {
        events.forEach(event -> {
            publisher.publishEvent(event);
        });
    }

}
