package at.fhv.ecommerce.product.write.infrastructure.messaging;

import java.util.List;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import at.fhv.ecommerce.common.domain.DomainEvent;
import at.fhv.ecommerce.product.write.domain.event.ProductCreated;
import at.fhv.ecommerce.product.write.domain.port.ProductEventPublisher;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KafkaProductEventAdapter implements ProductEventPublisher {
    private final StreamBridge stream;

    @Override
    public void publishAll(List<DomainEvent> events) {
        events.forEach(event -> {
            if (event instanceof ProductCreated evt) {
                var integration = new ProductIntegration(
                    evt.id(),
                    evt.productId().value(),
                    "CREATED"
                );
                stream.send("product-out-0", integration);
            }
        });
    }

}
