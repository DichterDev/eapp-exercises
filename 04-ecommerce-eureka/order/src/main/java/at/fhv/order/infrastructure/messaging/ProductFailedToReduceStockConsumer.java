package at.fhv.order.infrastructure.messaging;

import at.fhv.common.domain.event.product.ProductFailedToReduceStock;
import at.fhv.order.application.command.FailOrder;
import at.fhv.order.application.handler.OrderCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class ProductFailedToReduceStockConsumer {
    private final OrderCommandHandler command;

    @Bean
    public Consumer<ProductFailedToReduceStock> productFailedToReduceStockIn() {
        return event -> command.fail(new FailOrder(event.orderId()));
    }
}