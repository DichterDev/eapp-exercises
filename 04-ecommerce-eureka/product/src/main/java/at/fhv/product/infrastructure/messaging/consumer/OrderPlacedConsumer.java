package at.fhv.product.infrastructure.messaging.consumer;

import at.fhv.common.domain.event.order.OrderPlaced;
import at.fhv.common.domain.event.product.ProductFailedToReduceStock;
import at.fhv.product.application.command.ReduceProductStock;
import at.fhv.product.application.handler.ProductCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class OrderPlacedConsumer {

    private final ProductCommandHandler command;

    @Bean
    public Consumer<OrderPlaced> orderPlacedIn() {
        return event -> {
            try {
                event.orderItems()
                    .forEach(
                        (productId, amount) -> command
                            .reduceStock(new ReduceProductStock(productId, amount, event.orderId()))
                    );
            } catch (Exception ex) {
                throw ex;
            }
        };
    }
}
