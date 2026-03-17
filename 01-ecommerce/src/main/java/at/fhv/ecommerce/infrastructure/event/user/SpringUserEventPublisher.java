package at.fhv.ecommerce.infrastructure.event.user;

import java.util.List;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import at.fhv.ecommerce.domain.common.DomainEvent;
import at.fhv.ecommerce.domain.user.ports.UserEventPublisher;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SpringUserEventPublisher implements UserEventPublisher {
    private final ApplicationEventPublisher publisher;

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(event -> {
            publisher.publishEvent(event);
        });
    }

}
