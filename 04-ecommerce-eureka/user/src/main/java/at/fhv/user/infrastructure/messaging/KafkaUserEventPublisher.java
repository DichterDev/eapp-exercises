package at.fhv.user.infrastructure.messaging;


import at.fhv.common.domain.event.DomainEvent;
import at.fhv.common.domain.event.user.UserCartCheckedOut;
import at.fhv.user.domain.port.UserEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaUserEventPublisher implements UserEventPublisher {
    private final StreamBridge stream;


    @Override
    public void publishAll(List<DomainEvent> events) {
        for (DomainEvent event : events) {
            if (event instanceof UserCartCheckedOut) {
                stream.send("kafka-user-checkout-out-0", event);
            } else {
                stream.send("kafka-user-event", event);
            }
        }
    }
}
