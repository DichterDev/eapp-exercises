package at.fhv.product.infrastructure.messaging;

import at.fhv.common.domain.event.DomainEvent;
import at.fhv.common.domain.event.product.ProductFailedToReduceStock;
import at.fhv.common.domain.event.product.ProductReducedStock;
import at.fhv.product.domain.port.ProductEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaProductEventPublisher implements ProductEventPublisher {
    private final StreamBridge stream;

    @Override
    public void publishAll(List<DomainEvent> events) {
        for (DomainEvent event : events) {
            if (event instanceof ProductReducedStock) {
                stream.send("kafka-product-reduced-out-0", event);
            } else if (event instanceof ProductFailedToReduceStock) {
                stream.send("kafka-product-failed-out-0", event);
            } else {
                stream.send("kafka-product-event", event);
            }
        }
    }
}