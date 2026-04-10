package at.fhv.order.infrastructure.messaging;

import at.fhv.common.domain.event.DomainEvent;
import at.fhv.common.domain.event.order.OrderPlaced;
import at.fhv.order.domain.port.OrderEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaOrderEventPublisher implements OrderEventPublisher {
    private final StreamBridge stream;

    @Override
    public void publishAll(List<DomainEvent> events) {
        for (DomainEvent event : events) {
            if (event instanceof OrderPlaced) {
                stream.send("kafka-order-placed-out-0", event);
            } else {
                stream.send("kafka-order-event-out-0", event);
            }
        }
    }
}
